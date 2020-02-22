package org.tools.ppmtool.data.models;

import javax.persistence.*;
import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project extends BaseUuidEntity {

    private static final long serialVersionUID = -2027527972013891058L;

    @Column(name = "project_identifier", updatable = false, unique = true)
    private String projectIdentifier;

    @Column(name = "project_name", nullable = false, unique = true)
    private String projectName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "project")
    private Backlog backlog;
}