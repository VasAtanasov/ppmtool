package org.tools.ppmtool.service.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tools.ppmtool.data.models.User;
import org.tools.ppmtool.service.models.ProjectServiceModel;
import org.tools.ppmtool.web.models.requests.ProjectCreateRequest;

public interface ProjectService {
  ProjectServiceModel saveOrUpdateProject(ProjectCreateRequest projectRequest, User username);

  ProjectServiceModel findProjectByIdentifier(String projectIdentifier, User creator);

  Page<ProjectServiceModel> findAllProjects(Pageable pageable, User creator);

  void deleteProjectByIdentifier(String projectIdentifier, User creator);
}
