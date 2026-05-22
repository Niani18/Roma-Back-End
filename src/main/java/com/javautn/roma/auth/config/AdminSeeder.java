package com.javautn.roma.auth.config;

import com.javautn.roma.auth.entity.Role;
import com.javautn.roma.auth.entity.UserEntity;
import com.javautn.roma.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("admin").isPresent()) {
            return;
        }

        UserEntity admin = new UserEntity(
                "admin",
                passwordEncoder.encode("1234"),
                Role.ADMIN,
                null
        );

        userRepository.save(admin);
    }
}
