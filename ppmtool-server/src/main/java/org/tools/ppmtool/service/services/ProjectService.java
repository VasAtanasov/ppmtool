package org.tools.ppmtool.service.services;

import org.tools.ppmtool.data.models.Project;

public interface ProjectService {
    Project saveOrUpdateProject(final Project project);
}