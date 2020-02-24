package org.tools.ppmtool.service.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tools.ppmtool.data.models.Backlog;
import org.tools.ppmtool.data.models.ProjectTask;
import org.tools.ppmtool.data.models.User;
import org.tools.ppmtool.data.repositories.ProjectRepository;
import org.tools.ppmtool.data.repositories.ProjectTaskRepository;
import org.tools.ppmtool.exceptions.ProjectNotFoundException;
import org.tools.ppmtool.service.models.ProjectTaskServiceModel;
import org.tools.ppmtool.service.services.ProjectTaskService;
import org.tools.ppmtool.utils.ModelMapperWrapper;
import org.tools.ppmtool.web.models.requests.ProjectTaskCreateRequest;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProjectTaskServiceImpl implements ProjectTaskService {

  private final ProjectTaskRepository projectTaskRepository;
  private final ProjectRepository projectRepository;
  private final ModelMapperWrapper modelMapper;

  @Override
  public ProjectTaskServiceModel addProjectTask(
      String projectIdentifier, ProjectTaskCreateRequest projectTaskRequest, User creator) {

    try {

      Backlog backlog =
          projectRepository
              .findByProjectIdentifierAndProjectLeader(projectIdentifier, creator.getUsername())
              .orElseThrow(() -> new ProjectNotFoundException("Project not found in your account."))
              .getBacklog();

      ProjectTask projectTask = modelMapper.map(projectTaskRequest, ProjectTask.class);
      projectTask.setBacklog(backlog);

      Integer backlogSequence = backlog.getPTSequence();
      backlogSequence++;

      backlog.setPTSequence(backlogSequence);

      projectTask.setProjectSequence(backlog.getProjectIdentifier() + "-" + backlogSequence);
      projectTask.setProjectIdentifier(projectIdentifier);

      if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
        projectTask.setStatus("TO_DO");
      }

      if (projectTask.getPriority() == null || projectTask.getPriority() == 0) { // In
        projectTask.setPriority(3);
      }

      return modelMapper.map(
          projectTaskRepository.save(projectTask), ProjectTaskServiceModel.class);
    } catch (Exception e) {
      throw new ProjectNotFoundException("Project not Found");
    }
  }

  @Override
  public List<ProjectTaskServiceModel> findBacklogById(String projectIdentifier, User creator) {

    Backlog backlog =
        projectRepository
            .findByProjectIdentifierAndProjectLeader(projectIdentifier, creator.getUsername())
            .orElseThrow(() -> new ProjectNotFoundException("Project not found in your account."))
            .getBacklog();

    return backlog.getProjectTasks().stream()
        .sorted((a, b) -> Integer.compare(a.getPriority(), b.getPriority()))
        .map(task -> modelMapper.map(task, ProjectTaskServiceModel.class))
        .collect(Collectors.toList());
  }

  @Override
  public ProjectTaskServiceModel findBacklogTask(
      String projectIdentifier, String taskSequence, User creator) {

    if (!projectRepository.existsByProjectIdentifierAndProjectLeader(
        projectIdentifier, creator.getUsername())) {
      throw new ProjectNotFoundException("Project not found in your account.");
    }

    ProjectTask projectTask =
        projectTaskRepository
            .findByProjectSequence(taskSequence)
            .orElseThrow(
                () ->
                    new ProjectNotFoundException("Project Task '" + taskSequence + "' not found"));

    if (!projectTask.getProjectIdentifier().equals(projectIdentifier)) {
      throw new ProjectNotFoundException(
          "Project Task '" + taskSequence + "' does not exist in project: '" + projectIdentifier);
    }

    return modelMapper.map(projectTask, ProjectTaskServiceModel.class);
  }

  @Override
  public ProjectTaskServiceModel updateProjectTask(
      ProjectTaskServiceModel updatedTask,
      String projectIdentifier,
      String taskSequence,
      User creator) {

    if (!projectRepository.existsByProjectIdentifierAndProjectLeader(
        projectIdentifier, creator.getUsername())) {
      throw new ProjectNotFoundException("Project not found in your account.");
    }

    ProjectTask projectTaskUpdated = modelMapper.map(updatedTask, ProjectTask.class);

    ProjectTask projectTask =
        projectTaskRepository
            .findByProjectSequence(taskSequence)
            .orElseThrow(
                () ->
                    new ProjectNotFoundException("Project Task '" + taskSequence + "' not found"));

    projectTask.setProjectSequence(projectTaskUpdated.getProjectSequence());
    projectTask.setSummary(projectTaskUpdated.getSummary());
    projectTask.setAcceptanceCriteria(projectTaskUpdated.getAcceptanceCriteria());
    projectTask.setStatus(projectTaskUpdated.getStatus());
    projectTask.setPriority(projectTaskUpdated.getPriority());
    projectTask.setDueDate(projectTaskUpdated.getDueDate());

    return modelMapper.map(projectTaskRepository.save(projectTask), ProjectTaskServiceModel.class);
  }

  @Override
  public void deleteProjectTask(String projectIdentifier, String taskSequence, User creator) {

    if (!projectRepository.existsByProjectIdentifierAndProjectLeader(
        projectIdentifier, creator.getUsername())) {
      throw new ProjectNotFoundException("Project not found in your account.");
    }

    ProjectTask projectTask =
        projectTaskRepository
            .findByProjectSequence(taskSequence)
            .orElseThrow(
                () ->
                    new ProjectNotFoundException("Project Task '" + taskSequence + "' not found"));

    projectTaskRepository.delete(projectTask);
  }
}
