package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception{
        roleRepository.save(new Role("USER"));

        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("jim@jim.com", "password", "Jim", "Jimmerson", true, "jim", "240-477-0000", "05/16/1992", "USA", "Jim", "Jimmerson", 123456789);

        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("admin@admin.com", "password", "Admin", "User", true, "admin", "301-999-9999", "N/A", "N/A");
        user.setRoles(Arrays.asList(adminRole, userRole));
        userRepository.save(user);

        Flight flight = new Flight("Dallas, TX", "Dulles International, Washington D.C.", 300, "10/16/2019", "10/16/2019", "9:00am", "10:00pm", false, false, "Business", 300, 25, false);
        flightRepository.save(flight);

    }

}
