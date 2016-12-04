package thymeleafexamples.layouts.task;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostConstruct
    private void init() {
        taskRepository.save(new Task("Shopping", "Buy Milk and Butter...", "2017.01.01 13:22:42"));
        taskRepository.save(new Task("Books", "Read 'Lords of The Ring'", "2017.01.02 15:22:42"));
    }

    Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findOne(Long id) {
        return taskRepository.findOne(id);
    }
}
