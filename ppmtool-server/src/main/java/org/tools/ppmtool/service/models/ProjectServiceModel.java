package org.tools.ppmtool.service.models;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectServiceModel {
  private String id;
  private String projectIdentifier;
  private String projectName;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;
  private BacklogServiceMode backlog;
  private String projectLeader;
}
