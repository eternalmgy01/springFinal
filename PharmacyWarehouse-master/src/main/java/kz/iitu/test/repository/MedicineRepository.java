package kz.iitu.test.repository;

import kz.iitu.test.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findAll();
    Medicine findByNameAndManufacturer(String name, String manufacturer);
    boolean existsByNameAndManufacturer(String name, String manufacturer);
    boolean existsByName(String name);

}
