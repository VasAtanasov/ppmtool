package org.tools.ppmtool.service.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tools.ppmtool.data.models.Backlog;
import org.tools.ppmtool.data.models.ProjectTask;
import org.tools.ppmtool.data.repositories.BacklogRepository;
import org.tools.ppmtool.data.repositories.ProjectTaskRepository;
import org.tools.ppmtool.service.models.ProjectTaskServiceModel;
import org.tools.ppmtool.service.services.ProjectTaskService;
import org.tools.ppmtool.utils.ModelMapperWrapper;
import org.tools.ppmtool.web.models.requests.ProjectTaskCreateRequest;
import org.tools.ppmtool.exceptions.ProjectIdException;
import org.tools.ppmtool.exceptions.ProjectNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class ProjectTaskServiceImpl implements ProjectTaskService {
    private final ProjectTaskRepository projectTaskRepository;
    private final BacklogRepository backlogRepository;
    private final ModelMapperWrapper modelMapper;

    @Override
    public ProjectTaskServiceModel addProjectTask(String projectIdentifier,
            ProjectTaskCreateRequest projectTaskRequest) {

        try {
            // PTs to be added to a specific project, project != null, BL exists
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier)
                    .orElseThrow(() -> new ProjectIdException("Project ID '" + projectIdentifier + "' does not exist"));

            // set the bl to pt

            ProjectTask projectTask = modelMapper.map(projectTaskRequest, ProjectTask.class);
            projectTask.setBacklog(backlog);

            // we want our project sequence to be like this: IDPRO-1 IDPRO-2 ...100 101
            Integer backlogSequence = backlog.getPTSequence();
            // Update the BL SEQUENCE
            backlogSequence++;

            backlog.setPTSequence(backlogSequence);

            // Add Sequence to Project Task
            projectTask.setProjectSequence(backlog.getProjectIdentifier() + "-" + backlogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            // INITIAL priority when priority null

            // INITIAL status when status is null
            if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
                projectTask.setStatus("TO_DO");
            }

            if (projectTask.getPriority() == null) { // In the future we need projectTask.getPriority()== 0 to handle
                                                     // the form
                projectTask.setPriority(3);
            }

            return modelMapper.map(projectTaskRepository.save(projectTask), ProjectTaskServiceModel.class);
        } catch (

        Exception e) {
            throw new ProjectNotFoundException("Project not Found");
        }
    }

    @Override
    public List<ProjectTaskServiceModel> findBacklogById(String projectIdentifier) {
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier)
                .orElseThrow(() -> new ProjectIdException("Project ID '" + projectIdentifier + "' does not exist"));

        return backlog.getProjectTasks()
                .stream()
                .sorted((a,b) -> Integer.compare(a.getPriority(), b.getPriority()))
                .map(task -> modelMapper.map(task, ProjectTaskServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectTaskServiceModel findBacklogTask(String backlogId, String taskSequence) {
        getBacklog(backlogId);

        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(taskSequence)
                .orElseThrow(() -> new ProjectNotFoundException("Project Task '" + taskSequence + "' not found"));

        if (!projectTask.getProjectIdentifier().equals(backlogId)) {
            throw new ProjectNotFoundException(
                    "Project Task '" + taskSequence + "' does not exist in project: '" + backlogId);
        }

        return modelMapper.map(projectTask, ProjectTaskServiceModel.class);
    }

    @Override
    public ProjectTaskServiceModel updateProjectTask(ProjectTaskServiceModel updatedTask, String projectIdentifier,
            String taskSequence) {
        getBacklog(projectIdentifier);

        ProjectTask projectTaskUpdated = modelMapper.map(updatedTask, ProjectTask.class);

        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(taskSequence)
                .orElseThrow(() -> new ProjectNotFoundException("Project Task '" + taskSequence + "' not found"));

        projectTask.setProjectSequence(projectTaskUpdated.getProjectSequence());
        projectTask.setSummary(projectTaskUpdated.getSummary());
        projectTask.setAcceptanceCriteria(projectTaskUpdated.getAcceptanceCriteria());
        projectTask.setStatus(projectTaskUpdated.getStatus());
        projectTask.setPriority(projectTaskUpdated.getPriority());
        projectTask.setDueDate(projectTaskUpdated.getDueDate());

        return modelMapper.map(projectTaskRepository.save(projectTask), ProjectTaskServiceModel.class);
    }

    @Override
    public void deleteProjectTask(String projectIdentifier, String taskSequence) {
        backlogRepository.findByProjectIdentifier(projectIdentifier)
                .orElseThrow(() -> new ProjectIdException("Project ID '" + projectIdentifier + "' does not exist"));

        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(taskSequence)
                .orElseThrow(() -> new ProjectNotFoundException("Project Task '" + taskSequence + "' not found"));

        projectTaskRepository.delete(projectTask);
    }

    private Backlog getBacklog(String backlogId) {
        return backlogRepository.findByProjectIdentifier(backlogId)
                .orElseThrow(() -> new ProjectIdException("Project ID '" + backlogId + "' does not exist"));
    }
}