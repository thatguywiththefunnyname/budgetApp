package com.project.practice.service;

import com.project.practice.entities.Users;
import com.project.practice.repositories.UserRepo;
import com.project.practice.verification.PasswordValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Users saveUser(Users user) {
        return userRepo.save(user);
    }

    @Override
    public Optional<Users> getUserById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public boolean changePassword(Long id, String oldPassword, String newPassword) {
        Optional<Users> userOptional = userRepo.findById(id);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();

            if (PasswordValidator.validatePassword(newPassword)) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepo.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkOriginalPassword(String password) {
        return PasswordValidator.validatePassword(password);
    }

}
