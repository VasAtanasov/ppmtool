package org.tools.ppmtool.service.models;


import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectServiceModel {

    private Long id;
    private String projectName;
    private String projectIdentifier;
    private String description;
    private Date startDate;
    private Date endDate;

}
