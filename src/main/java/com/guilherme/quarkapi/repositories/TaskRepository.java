package com.guilherme.quarkapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilherme.quarkapi.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
