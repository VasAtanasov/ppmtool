package org.tools.ppmtool.service.services;

import java.util.List;

import org.tools.ppmtool.service.models.ProjectTaskServiceModel;
import org.tools.ppmtool.web.models.requests.ProjectTaskCreateRequest;

public interface ProjectTaskService {
    ProjectTaskServiceModel addProjectTask(String projectIdentifier, ProjectTaskCreateRequest projectTaskRequest);

    List<ProjectTaskServiceModel> findBacklogById(String projectIdentifier);

    ProjectTaskServiceModel findBacklogTask(String backlogId, String taskSequence);

    void deleteProjectTask(String backlogId, String taskSequence);

    ProjectTaskServiceModel updateProjectTask(ProjectTaskServiceModel updatedTask, String backlogId,
            String taskSequence);
}