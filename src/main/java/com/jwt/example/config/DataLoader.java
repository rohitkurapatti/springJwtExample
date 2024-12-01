package com.jwt.example.config;

import com.jwt.example.entity.Role;
import com.jwt.example.entity.UserModel;
import com.jwt.example.repository.RoleRepository;
import com.jwt.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Role admin = new Role();
        admin.setRoleName("ADMIN");
        Role user = new Role();
        user.setRoleName("USER");

        Role adminSaved = roleRepository.save(admin);
        Role userSaved = roleRepository.save(user);

        UserModel rohit = new UserModel();
        rohit.setEmailId("rohit@gmail.com");
        rohit.setPass("rohit");
        rohit.setRole(adminSaved);

        UserModel sneha = new UserModel();
        sneha.setEmailId("sneha@gmail.com");
        sneha.setPass("sneha");
        sneha.setRole(userSaved);



        userRepository.save(rohit);
        userRepository.save(sneha);
    }
}
