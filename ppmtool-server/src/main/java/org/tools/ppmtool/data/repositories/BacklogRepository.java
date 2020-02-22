package org.tools.ppmtool.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tools.ppmtool.data.models.Backlog;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, String> {

    Optional<Backlog> findByProjectIdentifier(String projectIdentifier);

}