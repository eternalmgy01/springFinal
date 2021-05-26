package com.example.spring.DAO;

import com.example.spring.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role, Long> {
    @Query(value = "SELECT * FROM roles r Where r.name=?1", nativeQuery = true)
    Role find(String name);
}
