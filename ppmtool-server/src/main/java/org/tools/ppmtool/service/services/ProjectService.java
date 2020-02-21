package org.tools.ppmtool.service.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tools.ppmtool.data.models.Project;
import org.tools.ppmtool.service.models.ProjectServiceModel;

public interface ProjectService {
    Project saveOrUpdateProject(final Project project);

    Project findProjectByIdentifier(String projectId);

    Page<ProjectServiceModel> findAllProjects(Pageable pageable);

    void deleteProjectByIdentifier(String projectId);
}