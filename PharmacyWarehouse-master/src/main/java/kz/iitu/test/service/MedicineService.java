package kz.iitu.test.service;

import kz.iitu.test.entity.Medicine;

import java.util.List;

public interface MedicineService {
    List<Medicine> findMedicines();
    void addMedicine(Medicine medicine);
    void deleteMedicine(Medicine medicine);
    void updateMedicine(Medicine medicine);
    Medicine getOne(Long id);
}
