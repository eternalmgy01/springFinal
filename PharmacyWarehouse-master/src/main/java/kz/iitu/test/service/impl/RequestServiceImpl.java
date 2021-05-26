package kz.iitu.test.service.impl;

import kz.iitu.test.entity.Firm;
import kz.iitu.test.entity.Medicine;
import kz.iitu.test.entity.Request;
import kz.iitu.test.repository.FirmRepository;
import kz.iitu.test.repository.MedicineRepository;
import kz.iitu.test.repository.RequestRepository;
import kz.iitu.test.service.FirmService;
import kz.iitu.test.service.MedicineService;
import kz.iitu.test.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private FirmService firmService;

    @Override
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> findAccepted() {
        return requestRepository.findAllByAcceptedTrue();
    }

    @Override
    public List<Request> findPending() {
        return requestRepository.findAllByAcceptedFalse();
    }

    @Override
    public void makeRequest(Long medicineId, Long firmId, Long quantity) {
        Request request = new Request();
        Date date = new Date();
        Medicine medicine = medicineService.getOne(medicineId);
        Firm firm = firmService.getOne(firmId);
        Long total = Double.doubleToLongBits(medicine.getPrice() * quantity);
        request.setTotalPrice(total);
        request.setAmount(quantity);
        request.setDateOfRequest(date);
        request.setFirm(firm);
        request.setMedicine(medicine);
        requestRepository.saveAndFlush(request);
    }

    @Override
    public void acceptRequest(Request request) {
        request.setAccepted(true);
        Date date = new Date();
        request.setAcceptedDate(date);
        requestRepository.saveAndFlush(request);
    }

    @Override
    public void updateRequest(Request request) {
        requestRepository.saveAndFlush(request);
    }
}
