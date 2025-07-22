package org.example.impl;

import org.example.User;
import org.example.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Transactional
    public User createUser(String email, String rawPassword, String fullName) {
        User user = new User();
        user.setEmail(email);
        user.setPasswordHash(encoder.encode(rawPassword));
        user.setFullName(fullName);
        return repo.save(user);
    }
}
