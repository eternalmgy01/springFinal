package kz.iitu.test.service.impl;

import kz.iitu.test.entity.Medicine;
import kz.iitu.test.repository.MedicineRepository;
import kz.iitu.test.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicServiceImpl implements MedicineService {

    @Autowired
    MedicineRepository medicineRepository;

    @Override
    public List<Medicine> findMedicines() {
        return medicineRepository.findAll();
    }

    @Override
    public void addMedicine(Medicine medicine) {
        medicineRepository.saveAndFlush(medicine);
    }

    @Override
    public void deleteMedicine(Medicine medicine) {
        medicineRepository.delete(medicine);
    }

    @Override
    public void updateMedicine(Medicine medicine) {
        medicineRepository.saveAndFlush(medicine);
    }

    @Override
    public Medicine getOne(Long id) {
        return medicineRepository.getOne(id);
    }
}
