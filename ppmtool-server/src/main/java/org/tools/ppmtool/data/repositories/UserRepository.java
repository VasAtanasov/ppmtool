package org.tools.ppmtool.data.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tools.ppmtool.data.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

	Boolean existsByUsernameIgnoreCase(String username);
}

