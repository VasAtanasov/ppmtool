package org.tools.ppmtool.service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tools.ppmtool.data.models.Project;
import org.tools.ppmtool.data.repositories.ProjectRepository;
import org.tools.ppmtool.service.services.ProjectService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public Project saveOrUpdateProject(final Project project) {

        //Logic

        return projectRepository.save(project);
    }

}