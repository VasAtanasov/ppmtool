package org.tools.ppmtool.web.models.responses;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectResponseModel {

    private Long id;
    private String projectName;
    private String projectIdentifier;
    private String description;
    private Date startDate;
    private Date endDate;

}
