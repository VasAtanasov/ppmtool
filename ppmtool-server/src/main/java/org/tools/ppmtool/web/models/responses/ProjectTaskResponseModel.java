package org.tools.ppmtool.web.models.responses;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTaskResponseModel {
    private String id;
    private String projectSequence;
    private String summary;
    private String acceptanceCriteria;
    private String status;
    private Integer priority;
    private LocalDate dueDate;
    private String projectIdentifier;
    private String backlogId;
}