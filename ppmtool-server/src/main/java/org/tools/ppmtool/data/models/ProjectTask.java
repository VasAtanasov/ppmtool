package org.tools.ppmtool.data.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_tasks")
public class ProjectTask extends BaseUuidEntity {

    private static final long serialVersionUID = -6569448935156928908L;

    @Column(name = "project_sequence")
    private String projectSequence;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "acceptance_criteria")
    private String acceptanceCriteria;

    @Column(name = "status")
    private String status;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "project_identifier", updatable = false)
    private String projectIdentifier;

    @ManyToOne(targetEntity = Backlog.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "backlog_id", referencedColumnName = "id", nullable = false, updatable = false)
    private Backlog backlog;

}