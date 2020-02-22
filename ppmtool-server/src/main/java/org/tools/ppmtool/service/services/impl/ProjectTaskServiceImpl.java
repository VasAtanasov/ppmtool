package org.tools.ppmtool.service.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tools.ppmtool.data.models.Backlog;
import org.tools.ppmtool.data.models.Project;
import org.tools.ppmtool.data.models.ProjectTask;
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
    private final ModelMapperWrapper modelMapper;

    @Override
    public ProjectTaskServiceModel addProjectTask(ProjectTaskCreateRequest projectTaskRequest) {
        Project project = getProject(projectTaskRequest.getProjectId());

        if (project.getBacklog() == null) {
            Backlog backlog = new Backlog();
            project.setBacklog(backlog);
            backlog.setProject(project);
        }

        Backlog backlog = project.getBacklog();
        ProjectTask projectTask = modelMapper.map(projectTaskRequest, ProjectTask.class);

        projectTask.setBacklog(backlog);
        projectTask.setProject(project);
        backlog.getProjectTasks().add(projectTask);
        project.getProjectTasks().add(projectTask);

        Integer backlogSequence = backlog.getPTSequence();
        backlogSequence++;
        backlog.setPTSequence(backlogSequence);
        projectTask.setProjectSequence("TASK-" + backlogSequence);

        // INITIAL priority when priority null
        // if(projectTask.getPriority()==0||projectTask.getPriority()==null){
        // projectTask.setPriority(3);
        // }

        if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
            projectTask.setStatus("TO_DO");
        }

        return modelMapper.map(projectTaskRepository.save(projectTask), ProjectTaskServiceModel.class);
    }

    @Override
    public List<ProjectTaskServiceModel> findAllProjectProjectTasks(String projectId) {
        Project project = getProject(projectId);

        return project.getProjectTasks().stream().map(task -> modelMapper.map(task, ProjectTaskServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectTaskServiceModel findProjectProjectTask(String projectId, String taskId) {
        Project project = getProject(projectId);

        ProjectTask projectTask = projectTaskRepository.findById(taskId)
                .orElseThrow(() -> new ProjectNotFoundException("Project Task '" + taskId + "' not found"));

        if (!project.getProjectTasks().contains(projectTask)) {
            throw new ProjectNotFoundException(
                    "Project Task '" + taskId + "' does not exist in project: '" + projectId);
        }

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