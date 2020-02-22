package org.tools.ppmtool.service.models;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BacklogServiceMode {
    private Integer PTSequence;
    private List<ProjectTaskServiceModel> projectTasks = new ArrayList<>();
}