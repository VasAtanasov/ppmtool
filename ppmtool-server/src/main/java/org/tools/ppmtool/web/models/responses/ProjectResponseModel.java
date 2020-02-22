package org.tools.ppmtool.web.models.responses;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectResponseModel {
    private String id;
    private String projectName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private BacklogResponseModel backLog;
}
