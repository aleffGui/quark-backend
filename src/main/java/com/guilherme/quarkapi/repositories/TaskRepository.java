package com.guilherme.quarkapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.guilherme.quarkapi.models.Task;

import jakarta.transaction.Transactional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
	
	@Transactional
	@Modifying
    @Query("UPDATE Task t SET t.status = true WHERE t.id = :id")
    void markAsComplete(@Param("id") Long id);
	
	List<Task> findByUserId(Long id);
}
