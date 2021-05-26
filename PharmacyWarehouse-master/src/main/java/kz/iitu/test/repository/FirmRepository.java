package kz.iitu.test.repository;

import kz.iitu.test.entity.Firm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirmRepository extends JpaRepository<Firm, Long> {
    Firm findByUsername(String username);
}
