package org.tools.ppmtool.service.services;

import java.util.List;

import org.tools.ppmtool.data.models.Project;

public interface ProjectService {
    Project saveOrUpdateProject(final Project project);

    Project findProjectByIdentifier(String projectId);

    List<Project> findAllProjects();

    void deleteProjectByIdentifier(String projectId);
}