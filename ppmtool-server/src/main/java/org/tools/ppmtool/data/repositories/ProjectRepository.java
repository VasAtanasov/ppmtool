package org.tools.ppmtool.data.repositories;

import org.springframework.stereotype.Repository;
import org.tools.ppmtool.data.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long>  {

}
