package org.tools.ppmtool.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tools.ppmtool.service.services.MapValidationErrorService;
import org.tools.ppmtool.service.services.ProjectTaskService;
import org.tools.ppmtool.utils.ModelMapperWrapper;
import org.tools.ppmtool.web.models.requests.ProjectTaskCreateRequest;
import org.tools.ppmtool.web.models.responses.ProjectTaskResponseModel;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/backlog")
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class BacklogController {
    private final ProjectTaskService projectTaskService;
    private final MapValidationErrorService mapValidationErrorService;
    private final ModelMapperWrapper modelMapper;

    @PostMapping("/{projectId}")
    public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody ProjectTaskCreateRequest projectTaskCreateRequest,
            BindingResult result, @PathVariable String projectId) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null)
            return errorMap;

        return new ResponseEntity<ProjectTaskResponseModel>(modelMapper
                .map(projectTaskService.addProjectTask(projectTaskCreateRequest), ProjectTaskResponseModel.class),
                HttpStatus.CREATED);
    }

}