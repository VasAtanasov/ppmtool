package org.tools.ppmtool.data.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.*;

@Entity
@Table(name = "backlogs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Backlog extends BaseUuidEntity {

  private static final long serialVersionUID = -3124656829687969531L;

  @Column private Integer PTSequence = 0;

  @Column(name = "project_identifier", updatable = false)
  private String projectIdentifier;

  @OneToOne(fetch = FetchType.EAGER)
  @MapsId
  @JoinColumn(name = "id")
  private Project project;

  @OneToMany(
      cascade = CascadeType.REFRESH,
      fetch = FetchType.EAGER,
      mappedBy = "backlog",
      orphanRemoval = true)
  private List<ProjectTask> projectTasks = new ArrayList<>();
}
