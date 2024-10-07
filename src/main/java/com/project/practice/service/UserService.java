package com.project.practice.service;

import java.util.List;
import java.util.Optional;
import com.project.practice.entities.Users;

public interface UserService {

    Users saveUser(Users user);

    Optional<Users> getUserById(Long id);

    List<Users> getAllUsers();

    void deleteUser(Long id);

    boolean checkOriginalPassword(String password);

    boolean changePassword(Long id, String oldPassword, String newPassword);
}
