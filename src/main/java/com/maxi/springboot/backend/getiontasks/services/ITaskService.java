package com.maxi.springboot.backend.getiontasks.services;

import com.maxi.springboot.backend.getiontasks.entities.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskService {

    List<Task> getAllTasks();

    Optional<Task> getTaskById(Long id);

    Task saveTask(Task task);

    Optional<Task> deleteTaskById(Long id);
}
