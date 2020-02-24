package org.tools.ppmtool.service.services;

import java.util.List;
import org.tools.ppmtool.data.models.User;
import org.tools.ppmtool.service.models.ProjectTaskServiceModel;
import org.tools.ppmtool.web.models.requests.ProjectTaskCreateRequest;

public interface ProjectTaskService {
  ProjectTaskServiceModel addProjectTask(
      String projectIdentifier, ProjectTaskCreateRequest projectTaskRequest, User creator);

  List<ProjectTaskServiceModel> findBacklogById(String projectIdentifier, User creator);

  ProjectTaskServiceModel findBacklogTask(String backlogId, String taskSequence, User creator);

  void deleteProjectTask(String backlogId, String taskSequence, User creator);

  ProjectTaskServiceModel updateProjectTask(
      ProjectTaskServiceModel updatedTask, String backlogId, String taskSequence, User creator);
}
