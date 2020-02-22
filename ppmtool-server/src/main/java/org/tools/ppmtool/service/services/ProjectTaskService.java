package org.tools.ppmtool.service.services;

import org.tools.ppmtool.service.models.ProjectTaskServiceModel;
import org.tools.ppmtool.web.models.requests.ProjectTaskCreateRequest;

public interface ProjectTaskService {
    ProjectTaskServiceModel addProjectTask(ProjectTaskCreateRequest projectTaskRequest);

}