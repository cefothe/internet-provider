package com.it.different.courses.internetprovider.persistence.repository;

import com.it.different.courses.internetprovider.persistence.entity.Role;
import com.it.different.courses.internetprovider.persistence.entity.Role.RoleType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
}