package org.tools.ppmtool.service.models;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTaskServiceModel {
    private String id;
    private String projectSequence;
    private String summary;
    private String acceptanceCriteria;
    private String status;
    private Integer priority;
    private LocalDate dueDate;
}