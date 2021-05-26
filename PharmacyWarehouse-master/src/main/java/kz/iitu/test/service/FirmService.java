package kz.iitu.test.service;

import kz.iitu.test.entity.Firm;

import java.util.List;

public interface FirmService {
    List<Firm> findAll();
    void addFirm(Firm firm);
    Firm getOne(Long id);
}
