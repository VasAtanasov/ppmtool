package org.tools.ppmtool.service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tools.ppmtool.data.models.Project;
import org.tools.ppmtool.data.repositories.ProjectRepository;
import org.tools.ppmtool.exceptions.ProjectIdException;
import org.tools.ppmtool.service.models.ProjectServiceModel;
import org.tools.ppmtool.service.services.ProjectService;
import org.tools.ppmtool.utils.ModelMapperWrapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapperWrapper modelMapper;

    @Override
    public Project saveOrUpdateProject(final Project project) {

        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException(
                    "Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    @Override
    public Project findProjectByIdentifier(String projectId) {

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null) {
            throw new ProjectIdException("Project ID '" + projectId + "' does not exist");

        }

        return project;
    }

    @Override
    public Page<ProjectServiceModel> findAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable).map(project -> {
            return modelMapper.map(project, ProjectServiceModel.class);
        });
    }

    @Override
    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null) {
            throw new ProjectIdException("Cannot Project with ID '" + projectId + "'. This project does not exist");
        }

        projectRepository.delete(project);
    }

}