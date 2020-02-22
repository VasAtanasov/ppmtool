package org.tools.ppmtool.web.models.responses;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BacklogResponseModel {
    private String id;
    private Integer PTSequence;
    private List<ProjectTaskResponseModel> projectTasks = new ArrayList<>();
}