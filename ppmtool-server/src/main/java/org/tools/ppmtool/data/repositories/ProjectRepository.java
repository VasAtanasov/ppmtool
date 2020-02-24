package org.tools.ppmtool.data.repositories;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tools.ppmtool.data.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

  Optional<Project> findByProjectIdentifier(String projectIdentifier);

  Optional<Project> findByProjectIdentifierAndProjectLeader(
      String projectIdentifier, String projectLeader);

  boolean existsByProjectIdentifierAndProjectLeader(String projectIdentifier, String projectLeader);

  Page<Project> findByProjectLeader(String projectLeader, Pageable pageable);
}
