package com.maxi.springboot.backend.getiontasks.repositories;

import com.maxi.springboot.backend.getiontasks.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {
}
