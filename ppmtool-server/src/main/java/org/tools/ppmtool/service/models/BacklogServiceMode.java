package org.tools.ppmtool.service.models;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BacklogServiceMode {
    private String id;
    private Integer PTSequence;
    private String projectIdentifier;
    private List<ProjectTaskServiceModel> projectTasks = new ArrayList<>();
}