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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tools.ppmtool.data.models.Project;
import org.tools.ppmtool.service.services.MapValidationErrorService;
import org.tools.ppmtool.service.services.ProjectService;
import org.tools.ppmtool.utils.ModelMapperWrapper;
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
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null)
            return errorMap;

        Project project1 = projectService.saveOrUpdateProject(project);

        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {

        Project project = projectService.findProjectByIdentifier(projectId);

        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
        projectService.deleteProjectByIdentifier(projectId);

        return new ResponseEntity<String>("Project with ID: '" + projectId + "' was deleted", HttpStatus.OK);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<?> updateProject(@Valid @RequestBody Project project, BindingResult result) {
        return this.createNewProject(project, result);
    }
}
