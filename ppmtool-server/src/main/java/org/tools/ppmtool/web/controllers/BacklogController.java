package org.tools.ppmtool.web.controllers;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tools.ppmtool.data.models.User;
import org.tools.ppmtool.service.models.ProjectTaskServiceModel;
import org.tools.ppmtool.service.services.MapValidationErrorService;
import org.tools.ppmtool.service.services.ProjectTaskService;
import org.tools.ppmtool.utils.ModelMapperWrapper;
import org.tools.ppmtool.web.models.requests.ProjectTaskCreateRequest;
import org.tools.ppmtool.web.models.responses.ProjectTaskResponseModel;

@RestController
@RequestMapping("/backlog")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BacklogController {

  private final ProjectTaskService projectTaskService;
  private final MapValidationErrorService mapValidationErrorService;
  private final ModelMapperWrapper modelMapper;

  @PostMapping("/{backlogId}")
  public ResponseEntity<?> createProjectTask(
      @Valid @RequestBody ProjectTaskCreateRequest projectTaskCreateRequest,
      BindingResult result,
      @PathVariable String backlogId,
      @AuthenticationPrincipal User creator) {

    ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
    if (errorMap != null) return errorMap;

    return new ResponseEntity<ProjectTaskResponseModel>(
        modelMapper.map(
            projectTaskService.addProjectTask(backlogId, projectTaskCreateRequest, creator),
            ProjectTaskResponseModel.class),
        HttpStatus.CREATED);
  }

  @GetMapping("/{backlogId}")
  public ResponseEntity<?> getProjectProjectTasks(
      @PathVariable String backlogId, @AuthenticationPrincipal User creator) {
    return new ResponseEntity<>(
        projectTaskService.findBacklogById(backlogId, creator), HttpStatus.OK);
  }

  @GetMapping("/{backlogId}/{taskSequence}")
  public ResponseEntity<?> getProjectTask(
      @PathVariable String backlogId,
      @PathVariable String taskSequence,
      @AuthenticationPrincipal User creator) {

    ProjectTaskServiceModel projectTask =
        projectTaskService.findBacklogTask(backlogId, taskSequence, creator);

    return new ResponseEntity<ProjectTaskServiceModel>(projectTask, HttpStatus.OK);
  }

  @PatchMapping("/{backlogId}/{taskSequence}")
  public ResponseEntity<?> updateProjectTask(
      @Valid @RequestBody ProjectTaskCreateRequest projectTaskRequest,
      BindingResult result,
      @PathVariable String backlogId,
      @PathVariable String taskSequence,
      @AuthenticationPrincipal User creator) {

    ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
    if (errorMap != null) return errorMap;

    ProjectTaskServiceModel projectTaskServiceModel =
        modelMapper.map(projectTaskRequest, ProjectTaskServiceModel.class);

    ProjectTaskServiceModel updatedProjectTask =
        projectTaskService.updateProjectTask(
            projectTaskServiceModel, backlogId, taskSequence, creator);

    return new ResponseEntity<ProjectTaskResponseModel>(
        modelMapper.map(updatedProjectTask, ProjectTaskResponseModel.class), HttpStatus.OK);
  }

  @DeleteMapping("/{backlogId}/{taskSequence}")
  public ResponseEntity<?> deleteProjectTask(
      @PathVariable String backlogId,
      @PathVariable String taskSequence,
      @AuthenticationPrincipal User creator) {

    projectTaskService.deleteProjectTask(backlogId, taskSequence, creator);

    return new ResponseEntity<String>(
        "Project Task " + taskSequence + " was deleted successfully", HttpStatus.OK);
  }
}
