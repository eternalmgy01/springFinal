package com.example.spring.service;

import com.example.spring.DAO.RoleDAO;
import com.example.spring.DAO.UserDAO;
import com.example.spring.model.Role;
import com.example.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> findAllUsers() {
        return userDAO.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return userDAO.findById(id);
    }

    public User findUserByNickname(String nickname) {
        return userDAO.findUserByNickname(nickname);
    }

    public void createUser(User user) {
        User newUser = new User();
        newUser.setNickname(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setCash(user.getCash());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        if (newUser.getNickname().equals("admin")) {
            Role role = roleDAO.find("ADMIN");
            newUser.setRoles(Collections.singletonList(role));
        }
        else {
            Role role = roleDAO.find("USER");
            newUser.setRoles(Collections.singletonList(role));
        }
        userDAO.saveAndFlush(user);
    }

    public void updateUser(Long id, User user) {
        User userDb = userDAO.findById(id).orElse(null);
        if (userDb != null) {
            userDb.setEmail(user.getEmail());
            userDb.setCash(user.getCash());
            userDb.setPassword(passwordEncoder.encode(user.getPassword()));
            userDAO.saveAndFlush(userDb);
        }
    }

    public void deleteUser(Long id) {
        userDAO.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        User user = userDAO.findUserByNickname(nickname);
        if (user == null) {
            throw new UsernameNotFoundException(nickname + " not found!");
        }
        return user;
    }
}
