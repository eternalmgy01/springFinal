package kz.iitu.test.repository;

import kz.iitu.test.entity.Request;
import kz.iitu.test.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findDistinctByName(String name);
}
