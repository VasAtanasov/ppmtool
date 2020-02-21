package org.tools.ppmtool.data.models;

import javax.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "projects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project extends BaseUuidEntity {

    private static final long serialVersionUID = -2027527972013891058L;

    @Column(name = "project_name", nullable = false, unique = true)
    private String projectName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

}