package kz.iitu.test.service.impl;

import kz.iitu.test.entity.Firm;
import kz.iitu.test.entity.Role;
import kz.iitu.test.repository.FirmRepository;
import kz.iitu.test.service.FirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class FirmServiceImpl implements FirmService, UserDetailsService {

    @Autowired
    FirmRepository firmRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<Firm> findAll() {
        return firmRepository.findAll();
    }

    @Override
    public void addFirm(Firm firm) {
        firm.setPassword(passwordEncoder.encode(firm.getPassword()));
        firmRepository.save(firm);
    }

    @Override
    public Firm getOne(Long id) {
        return firmRepository.getOne(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Firm firm = firmRepository.findByUsername(s);
        if (firm == null) {
            throw new UsernameNotFoundException("User not found!!!");
        }
        return firm;
    }
}
