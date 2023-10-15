package com.guilherme.quarkapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.guilherme.quarkapi.enums.TaskPriority;
import com.guilherme.quarkapi.enums.UserRole;
import com.guilherme.quarkapi.models.Task;
import com.guilherme.quarkapi.models.User;
import com.guilherme.quarkapi.repositories.TaskRepository;
import com.guilherme.quarkapi.repositories.UserRepository;

import java.time.LocalDateTime;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        
    	if(userRepository.count() == 0) {
    		   User adminUser = new User("guilhermeferreira", "Guilherme", "Ferreira", passwordEncoder.encode("123456"), UserRole.ADMIN);
    	       User regularUser = new User("brunabrandao", "Bruna", "Brandão", passwordEncoder.encode("123456"), UserRole.USER);
    	       userRepository.save(adminUser);
    	       userRepository.save(regularUser);
    	       
    	       if(taskRepository.count() == 0) {
    	    	   Task task1 = new Task("Ministrar Curso de Angular", "ensinar os principais conceitos do Angular.", TaskPriority.HIGH, LocalDateTime.now(), false);
    	    	   task1.setResponsible(adminUser);
    	    	   
    	    	   Task task2 = new Task("Aprender Angular", "aprender os principais conceitos de Angular.", TaskPriority.MEDIUM, LocalDateTime.now().plusDays(1), false);
    	    	   task2.setResponsible(regularUser);
    	    	   
    	    	   taskRepository.save(task1);
    	    	   taskRepository.save(task2);
    	    	   
    	       }
    	}
    }
}
