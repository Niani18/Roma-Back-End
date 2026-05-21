package com.javautn.roma.auth.config;

import com.javautn.roma.auth.entity.Role;
import com.javautn.roma.auth.entity.UserEntity;
import com.javautn.roma.auth.repository.UserRepository;
import com.javautn.roma.human.entity.CitizenEntity;
import com.javautn.roma.human.service.HumanService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final HumanService humanService;

    public AdminSeeder(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            HumanService humanService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.humanService = humanService;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("admin").isPresent()) {
            return;
        }

        CitizenEntity citizen = humanService.getCitizen(1);

        UserEntity admin = new UserEntity(
                "admin",
                passwordEncoder.encode("1234"),
                Role.ADMIN,
                citizen
        );

        userRepository.save(admin);
    }
}