package thymeleafexamples.layouts.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class MessageRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

//    @PostConstruct
//    public void createMessages() {
//        LOGGER.warn("Creating dummy messages...");
//        save(new Task("Lorem ipsum dolor sit amet.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis tristique metus at risus gravida facilisis. In porta interdum pulvinar. In."));
//        save(new Task("Lorem ipsum dolor sit amet.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse bibendum turpis et venenatis tincidunt. Vivamus tristique tortor vel pretium porttitor."));
//        save(new Task("Lorem ipsum dolor sit amet.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sed fermentum nisl. Sed elit dui."));
//    }

    @Transactional
    public Message save(Message message) {
        entityManager.persist(message);
        return message;
    }

    public List<Message> findAll() {
        return entityManager.createQuery("SELECT m FROM Message m", Message.class).getResultList();
    }

    public Message findById(Long id) {
        return entityManager.find(Message.class, id);
    }
}
