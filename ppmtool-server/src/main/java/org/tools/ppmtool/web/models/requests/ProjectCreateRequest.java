package org.tools.ppmtool.web.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectCreateRequest {

  private String id;

  @NotBlank(message = "Project name is required")
  private String projectName;

  private String projectIdentifier;

  @NotBlank(message = "Project description is required")
  private String description;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate startDate;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate endDate;
}
