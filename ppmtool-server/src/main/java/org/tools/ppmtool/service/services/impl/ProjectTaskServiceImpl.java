package org.tools.ppmtool.service.services.impl;

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

        // Exceptions: Project not found
        Project project = projectRepository.findById(projectTaskRequest.getProjectId()).orElseThrow(
                () -> new ProjectIdException("Project ID '" + projectTaskRequest.getProjectId() + "' does not exist"));

        // PTs to be added to a specific project, project != null, BL exists

        if (project.getBacklog() == null) {
            Backlog backlog = new Backlog();
            project.setBacklog(backlog);
            backlog.setProject(project);
        }

        Backlog backlog = project.getBacklog();
        // set the bl to pt

        ProjectTask projectTask = modelMapper.map(projectTaskRequest, ProjectTask.class);

        projectTask.setBacklog(backlog);
        projectTask.setProject(project);
        backlog.getProjectTasks().add(projectTask);
        project.getProjectTasks().add(projectTask);
        // we want our project sequence to be like this: IDPRO-1 IDPRO-2 ...100 101
        Integer backlogSequence = backlog.getPTSequence();
        // Update the BL SEQUENCE
        backlogSequence++;
        backlog.setPTSequence(backlogSequence);

        // Add Sequence to Project Task
        projectTask.setProjectSequence("TASK-" + backlogSequence);

        // INITIAL priority when priority null
        // if(projectTask.getPriority()==0||projectTask.getPriority()==null){
        // projectTask.setPriority(3);
        // }
        // INITIAL status when status is null
        if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
            projectTask.setStatus("TO_DO");
        }

        return modelMapper.map(projectTaskRepository.save(projectTask), ProjectTaskServiceModel.class);
    }
}