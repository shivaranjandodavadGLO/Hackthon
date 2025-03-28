package com.hackthon.auth.service;

import com.hackthon.auth.dto.LoginDTO;
import com.hackthon.auth.dto.UserDTO;
import com.hackthon.auth.entity.UserProfile;
import com.hackthon.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserProfile registerUser(UserDTO userDTO) {
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword()); // Encode the password
        UserProfile newUser = new UserProfile(
                userDTO.getEmail(),
                encodedPassword,
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getRole()
        );

        return userRepository.save(newUser);
    }

    public boolean authenticateUser(LoginDTO loginDTO) {
        Optional<UserProfile> user = userRepository.findByEmail(loginDTO.getEmail());
        return user.isPresent() && passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword());
    }

    public Optional<UserProfile> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
