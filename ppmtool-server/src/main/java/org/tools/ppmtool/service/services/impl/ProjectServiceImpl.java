package org.tools.ppmtool.service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tools.ppmtool.data.models.Backlog;
import org.tools.ppmtool.data.models.Project;
import org.tools.ppmtool.data.repositories.ProjectRepository;
import org.tools.ppmtool.exceptions.ProjectIdException;
import org.tools.ppmtool.service.models.ProjectServiceModel;
import org.tools.ppmtool.service.services.ProjectService;
import org.tools.ppmtool.utils.ModelMapperWrapper;
import org.tools.ppmtool.web.models.requests.ProjectCreateRequest;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapperWrapper modelMapper;

    @Override
    public ProjectServiceModel saveOrUpdateProject(ProjectCreateRequest projectRequest) {
        Project project = null;

        if (projectRequest.getId() == null) {
            project = modelMapper.map(projectRequest, Project.class);
            Backlog backlog = new Backlog();
            backlog.setProject(project);
            project.setBacklog(backlog);
        } else {
            project = projectRepository.findById(projectRequest.getId()).map(p -> {
                p.setProjectName(projectRequest.getProjectName());
                p.setDescription(projectRequest.getDescription());
                p.setStartDate(projectRequest.getStartDate());
                p.setEndDate(projectRequest.getEndDate());
                return p;
            }).orElseThrow(() -> new ProjectIdException("Project ID '" + projectRequest.getId() + "' does not exist"));
        }

        try {
            return modelMapper.map(projectRepository.save(project), ProjectServiceModel.class);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getId() + "' already exists");
        }
    }

    @Override
    public ProjectServiceModel findProjectById(String projectId) {
        return projectRepository.findById(projectId).map(project -> {
            return modelMapper.map(project, ProjectServiceModel.class);
        }).orElseThrow(() -> new ProjectIdException("Project ID '" + projectId + "' does not exist"));
    }

    @Override
    public Page<ProjectServiceModel> findAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable).map(project -> {
            return modelMapper.map(project, ProjectServiceModel.class);
        });
    }

    @Override
    public void deleteProjectById(String projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectIdException(
                "Cannot Project with ID '" + projectId + "'. This project does not exist"));

        projectRepository.delete(project);
    }

}