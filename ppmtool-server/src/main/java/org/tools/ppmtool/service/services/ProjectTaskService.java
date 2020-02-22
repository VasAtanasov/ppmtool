package org.tools.ppmtool.service.services;

import java.util.List;

import org.tools.ppmtool.service.models.ProjectTaskServiceModel;
import org.tools.ppmtool.web.models.requests.ProjectTaskCreateRequest;

public interface ProjectTaskService {
    ProjectTaskServiceModel  addProjectTask(String projectIdentifier,ProjectTaskCreateRequest projectTaskRequest);

    List<ProjectTaskServiceModel> findAllProjectProjectTasks(String projectId);

    ProjectTaskServiceModel findProjectProjectTask(String projectId, String taskId);

    void deleteProjectTask(String taskId);

    ProjectTaskServiceModel updateProjectTask(ProjectTaskServiceModel updatedTask, String projectId, String taskId);
}