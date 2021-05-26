package kz.iitu.test.repository;

import kz.iitu.test.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAll();

    List<Request> findAllByAcceptedTrue();
    List<Request> findAllByAcceptedFalse();
}
