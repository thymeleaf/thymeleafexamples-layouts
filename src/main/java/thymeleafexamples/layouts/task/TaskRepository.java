package thymeleafexamples.layouts.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class TaskRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Task save(Task task) {
        entityManager.persist(task);
        return task;
    }

    public List<Task> findAll() {
        return entityManager.createQuery("SELECT t FROM Task t", Task.class).getResultList();
    }

    public Task findById(Long id) {
        return entityManager.find(Task.class, id);
    }
}
