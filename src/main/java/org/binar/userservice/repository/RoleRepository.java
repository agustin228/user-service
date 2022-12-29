package org.binar.userservice.repository;


import org.binar.userservice.model.Roles;
import org.binar.userservice.model.enumerations.ERoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface repository untuk menghandle request ke table role di database
 * @author Agustinus
 */
@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByName(ERoles name);
}
