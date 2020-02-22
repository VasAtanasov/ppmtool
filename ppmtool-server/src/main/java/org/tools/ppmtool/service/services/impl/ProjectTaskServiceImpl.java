package org.tools.ppmtool.service.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tools.ppmtool.data.models.Backlog;
import org.tools.ppmtool.data.models.Project;
import org.tools.ppmtool.data.models.ProjectTask;
import org.tools.ppmtool.data.repositories.BacklogRepository;
import org.tools.ppmtool.data.repositories.ProjectRepository;
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
    private final ProjectRepository projectRepository;
    private final BacklogRepository backlogRepository;
    private final ModelMapperWrapper modelMapper;

    @Override
    public ProjectTaskServiceModel addProjectTask(String backlogId,
            ProjectTaskCreateRequest projectTaskRequest) {

        try {
            // PTs to be added to a specific project, project != null, BL exists
            Backlog backlog = backlogRepository.findByProjectIdentifier(backlogId)
                    .orElseThrow(() -> new ProjectNotFoundException("Project not Found"));
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
            projectTask.setProjectIdentifier(backlogId);

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
        } catch (Exception e) {
            throw new ProjectNotFoundException("Project not Found");
        }
    }

    @Override
    public List<ProjectTaskServiceModel> findAllProjectProjectTasks(String projectId) {
        Project project = getProject(projectId);

        // return project.getProjectTasks().stream().map(task -> modelMapper.map(task,
        // ProjectTaskServiceModel.class))
        // .collect(Collectors.toList());
        return null;
    }

    @Override
    public ProjectTaskServiceModel findProjectProjectTask(String projectId, String taskId) {
        Project project = getProject(projectId);

        ProjectTask projectTask = projectTaskRepository.findById(taskId)
                .orElseThrow(() -> new ProjectNotFoundException("Project Task '" + taskId + "' not found"));

        return modelMapper.map(projectTask, ProjectTaskServiceModel.class);
    }

    @Override
    public ProjectTaskServiceModel updateProjectTask(ProjectTaskServiceModel updatedTask, String projectId,
            String taskId) {

        ProjectTask projectTaskUpdated = modelMapper.map(updatedTask, ProjectTask.class);

        ProjectTask projectTask = projectTaskRepository.findById(taskId)
                .orElseThrow(() -> new ProjectNotFoundException("Project Task '" + taskId + "' not found"));

        projectTask.setProjectSequence(projectTaskUpdated.getProjectSequence());
        projectTask.setSummary(projectTaskUpdated.getSummary());
        projectTask.setAcceptanceCriteria(projectTaskUpdated.getAcceptanceCriteria());
        projectTask.setStatus(projectTaskUpdated.getStatus());
        projectTask.setPriority(projectTaskUpdated.getPriority());
        projectTask.setDueDate(projectTaskUpdated.getDueDate());

        return modelMapper.map(projectTaskRepository.save(projectTask), ProjectTaskServiceModel.class);
    }

    @Override
    public void deleteProjectTask(String taskId) {

        ProjectTask projectTask = projectTaskRepository.findById(taskId)
                .orElseThrow(() -> new ProjectNotFoundException("Project Task '" + taskId + "' not found"));

        projectTaskRepository.delete(projectTask);
    }

    private Project getProject(String projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectIdException("Project ID '" + projectId + "' does not exist"));
    }
}