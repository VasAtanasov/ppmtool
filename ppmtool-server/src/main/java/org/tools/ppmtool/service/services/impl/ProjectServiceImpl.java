package org.tools.ppmtool.service.services.impl;

import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tools.ppmtool.data.models.Backlog;
import org.tools.ppmtool.data.models.Project;
import org.tools.ppmtool.data.models.User;
import org.tools.ppmtool.data.repositories.ProjectRepository;
import org.tools.ppmtool.exceptions.ProjectIdException;
import org.tools.ppmtool.exceptions.ProjectNotFoundException;
import org.tools.ppmtool.service.models.ProjectServiceModel;
import org.tools.ppmtool.service.services.ProjectService;
import org.tools.ppmtool.utils.ModelMapperWrapper;
import org.tools.ppmtool.web.models.requests.ProjectCreateRequest;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository projectRepository;
  private final ModelMapperWrapper modelMapper;

  @Override
  public ProjectServiceModel saveOrUpdateProject(
      ProjectCreateRequest projectRequest, User creator) {

    Project project = null;

    if (projectRequest.getId() == null) {
      project = modelMapper.map(projectRequest, Project.class);
      Backlog backlog = new Backlog();
      backlog.setProject(project);
      project.setBacklog(backlog);
      String projectIdentifier = getSaltString();
      project.setProjectIdentifier(projectIdentifier);
      backlog.setProjectIdentifier(projectIdentifier);
    } else {
      project =
          projectRepository
              .findById(projectRequest.getId())
              .map(
                  p -> {
                    p.setProjectName(projectRequest.getProjectName());
                    p.setDescription(projectRequest.getDescription());
                    p.setStartDate(projectRequest.getStartDate());
                    p.setEndDate(projectRequest.getEndDate());
                    return p;
                  })
              .orElseThrow(
                  () ->
                      new ProjectIdException(
                          "Project ID '" + projectRequest.getId() + "' does not exist."));

      if (!project.getProjectLeader().equals(creator.getUsername())) {
        throw new ProjectNotFoundException("Project not found in your account.");
      }
    }

    project.setUser(creator);
    project.setProjectLeader(creator.getUsername());

    try {
      return modelMapper.map(projectRepository.save(project), ProjectServiceModel.class);
    } catch (Exception e) {
      throw new ProjectIdException("Project ID '" + project.getId() + "' already exists");
    }
  }

  @Override
  public ProjectServiceModel findProjectByIdentifier(String projectIdentifier, User creator) {
    ProjectServiceModel project =
        projectRepository
            .findByProjectIdentifier(projectIdentifier)
            .map(p -> modelMapper.map(p, ProjectServiceModel.class))
            .orElseThrow(
                () ->
                    new ProjectIdException(
                        "Project ID '" + projectIdentifier + "' does not exist"));

    if (!project.getProjectLeader().equals(creator.getUsername())) {
      throw new ProjectNotFoundException("Project not found in your account.");
    }

    return project;
  }

  @Override
  public Page<ProjectServiceModel> findAllProjects(Pageable pageable, User creator) {
    return projectRepository
        .findByProjectLeader(creator.getUsername(), pageable)
        .map(project -> modelMapper.map(project, ProjectServiceModel.class));
  }

  @Override
  public void deleteProjectByIdentifier(String projectId, User creator) {
    Project project =
        projectRepository
            .findByProjectIdentifierAndProjectLeader(projectId, creator.getUsername())
            .orElseThrow(
                () ->
                    new ProjectIdException(
                        "Cannot delete Project with ID '"
                            + projectId
                            + "'. This project does not exist"));

    projectRepository.delete(project);
  }

  private String getSaltString() {
    String SALT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    StringBuilder salt = new StringBuilder();
    Random rnd = new Random();
    while (salt.length() < 5) { // length of the random string.
      int index = (int) (rnd.nextFloat() * SALT_CHARS.length());
      salt.append(SALT_CHARS.charAt(index));
    }
    return salt.toString();
  }
}
