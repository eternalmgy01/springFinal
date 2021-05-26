package kz.iitu.test.service;

import kz.iitu.test.entity.Request;

import java.util.List;

public interface RequestService {
    List<Request> findAll();
    List<Request> findAccepted();
    List<Request> findPending();
    void makeRequest(Long medicineId, Long firmId, Long quantity);
    void acceptRequest(Request request);
    void updateRequest(Request request);
}
