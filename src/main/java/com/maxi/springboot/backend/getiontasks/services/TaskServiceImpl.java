package com.maxi.springboot.backend.getiontasks.services;

import com.maxi.springboot.backend.getiontasks.entities.Task;
import com.maxi.springboot.backend.getiontasks.repositories.ITaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements ITaskService{

    private final ITaskRepository taskRepository;

    public TaskServiceImpl(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Transactional
    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    @Override
    public Optional<Task> deleteTaskById(Long id) {
        Optional<Task> taskOp = taskRepository.findById(id);

        if (taskOp.isPresent()) {
            taskRepository.deleteById(id);
            return taskOp;
        }
        return Optional.empty();
    }
}
