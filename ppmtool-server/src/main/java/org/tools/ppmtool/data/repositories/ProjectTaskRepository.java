package org.tools.ppmtool.data.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tools.ppmtool.data.models.ProjectTask;

@Repository
public interface ProjectTaskRepository extends JpaRepository<ProjectTask, String> {

    List<ProjectTask> findByProjectIdentifierOrderByPriority(String projectIdentifier);

    Optional<ProjectTask> findByProjectSequence(String projectSequence);
}