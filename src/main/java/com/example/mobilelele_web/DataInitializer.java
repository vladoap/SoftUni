package com.example.mobilelele_web;

import com.example.mobilelele_web.model.entity.User;
import com.example.mobilelele_web.model.entity.UserRole;
import com.example.mobilelele_web.model.enums.Role;
import com.example.mobilelele_web.repository.UserRepository;
import com.example.mobilelele_web.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        initializeRoles();
        initializeUsers();

    }

    private void initializeRoles() {
        if (userRoleRepository.count() == 0) {
            UserRole adminRole = new UserRole();
            adminRole.setRole(Role.Admin);

            UserRole userRole = new UserRole();
            userRole.setRole(Role.User);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {

            UserRole adminRole = userRoleRepository.findByRole(Role.Admin);
            UserRole userRole = userRoleRepository.findByRole(Role.User);


            User admin = new User();
            admin.setIsActive(true);
            admin.setUsername("vladoap");
            admin.setFirstName("Vlado");
            admin.setLastName("Apostolov");
            admin.setPassword(passwordEncoder.encode("111111"));

            admin.setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);


            User gosho = new User();
            gosho.setIsActive(true);
            gosho.setUsername("gosho");
            gosho.setFirstName("Georgi");
            gosho.setLastName("Georgiev");
            gosho.setPassword(passwordEncoder.encode("111111"));

            gosho.setRoles(Set.of(userRole));
            userRepository.save(gosho);
        }
    }
}
