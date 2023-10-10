package com.guilherme.quarkapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilherme.quarkapi.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
