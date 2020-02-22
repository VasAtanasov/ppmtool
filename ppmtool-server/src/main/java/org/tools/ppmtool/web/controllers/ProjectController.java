package org.tools.ppmtool.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tools.ppmtool.service.services.MapValidationErrorService;
import org.tools.ppmtool.service.services.ProjectService;
import org.tools.ppmtool.utils.ModelMapperWrapper;
import org.tools.ppmtool.web.models.requests.ProjectCreateRequest;
import org.tools.ppmtool.web.models.responses.ProjectResponseModel;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class ProjectController {

    private final ProjectService projectService;
    private final MapValidationErrorService mapValidationErrorService;
    private final ModelMapperWrapper modelMapper;

    @GetMapping
    public ResponseEntity<?> getAllProjects(Pageable pageable) {
        return new ResponseEntity<>(projectService.findAllProjects(pageable).map(project -> {
            return modelMapper.map(project, ProjectResponseModel.class);
        }), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewProject(@Valid @RequestBody ProjectCreateRequest project, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null)
            return errorMap;

        return new ResponseEntity<ProjectResponseModel>(
                modelMapper.map(projectService.saveOrUpdateProject(project), ProjectResponseModel.class),
                HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectIdentifier) {
        return new ResponseEntity<ProjectResponseModel>(
                modelMapper.map(projectService.findProjectByIdentifier(projectIdentifier), ProjectResponseModel.class),
                HttpStatus.OK);
    }

    @DeleteMapping("/{projectIdentifier}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectIdentifier) {
        projectService.deleteProjectByIdentifier(projectIdentifier);

        return new ResponseEntity<String>("Project with ID: '" + projectIdentifier + "' was deleted", HttpStatus.OK);
    }
}
