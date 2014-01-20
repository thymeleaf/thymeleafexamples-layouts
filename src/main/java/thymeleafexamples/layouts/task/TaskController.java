package thymeleafexamples.layouts.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Layout Dialect usage example.
 */
@Controller
@Secured("ROLE_USER")
class TaskController {

    private TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        init();
    }

    private void init() {
        taskRepository.save(new Task("Shopping", "Buy Milk and Butter...", "2014.01.01 13:22:42"));
        taskRepository.save(new Task("Books", "Read 'Lords of The Ring'", "2014.01.02 15:22:42"));
    }

    @ModelAttribute("page")
    public String module() {
        return "tasks";
    }

    @RequestMapping(value = "task", method = RequestMethod.GET)
    public String messages(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "task/list";
    }
}
