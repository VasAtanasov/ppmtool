package org.tools.ppmtool.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tools.ppmtool.service.models.ProjectTaskServiceModel;
import org.tools.ppmtool.service.services.MapValidationErrorService;
import org.tools.ppmtool.service.services.ProjectTaskService;
import org.tools.ppmtool.utils.ModelMapperWrapper;
import org.tools.ppmtool.web.models.requests.ProjectTaskCreateRequest;
import org.tools.ppmtool.web.models.responses.ProjectTaskResponseModel;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/backlog")
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class ProjectTaskController {
    private final ProjectTaskService projectTaskService;
    private final MapValidationErrorService mapValidationErrorService;
    private final ModelMapperWrapper modelMapper;

    @PostMapping("/{projectId}")
    public ResponseEntity<?> createProjectTask(@Valid @RequestBody ProjectTaskCreateRequest projectTaskCreateRequest,
            BindingResult result, @PathVariable String projectId) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null)
            return errorMap;

        return new ResponseEntity<ProjectTaskResponseModel>(modelMapper
                .map(projectTaskService.addProjectTask(projectTaskCreateRequest), ProjectTaskResponseModel.class),
                HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectProjectTasks(@PathVariable String projectId) {
        return new ResponseEntity<>(projectTaskService.findAllProjectProjectTasks(projectId), HttpStatus.OK);
    }

    @GetMapping("/{projectId}/{taskId}")
    public ResponseEntity<?> getProjectTask(@PathVariable String projectId, @PathVariable String taskId) {
        ProjectTaskServiceModel projectTask = projectTaskService.findProjectProjectTask(projectId, taskId);
        return new ResponseEntity<ProjectTaskServiceModel>(projectTask, HttpStatus.OK);
    }

    @PatchMapping("/{projectId}/{taskId}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTaskCreateRequest projectTaskRequest,
            BindingResult result, @PathVariable String projectId, @PathVariable String taskId) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null)
            return errorMap;

        ProjectTaskServiceModel projectTaskServiceModel = modelMapper.map(projectTaskRequest,
                ProjectTaskServiceModel.class);
        ProjectTaskServiceModel updatedProjectTask = projectTaskService.updateProjectTask(projectTaskServiceModel,
                projectId, taskId);

        return new ResponseEntity<ProjectTaskResponseModel>(
                modelMapper.map(updatedProjectTask, ProjectTaskResponseModel.class), HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}/{taskId}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String projectId, @PathVariable String taskId) {
        projectTaskService.deleteProjectTask(taskId);

        return new ResponseEntity<String>("Project Task " + taskId + " was deleted successfully", HttpStatus.OK);
    }

}