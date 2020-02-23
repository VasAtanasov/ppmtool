package org.tools.ppmtool.web.models.requests;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectCreateRequest {

    private String id;

    @NotBlank(message = "Project name is required")
    private String projectName;

    @NotBlank(message = "Project description is required")
    private String description;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDate endDate;

}
