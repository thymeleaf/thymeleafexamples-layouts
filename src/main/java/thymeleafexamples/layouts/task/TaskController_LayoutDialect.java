package thymeleafexamples.layouts.task;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Layout Dialect usage example.
 */
@Controller
@Secured("ROLE_USER")
class TaskController_LayoutDialect {

    private final TaskService taskService;

    TaskController_LayoutDialect(TaskService taskService) {
        this.taskService = taskService;
    }

    @ModelAttribute("module")
    public String module() {
        return "tasks-ld";
    }

    @RequestMapping(value = "task-ld", method = RequestMethod.GET)
    public String tasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "task-ld/task-list";
    }

    @RequestMapping(value = "task-ld/{id}", method = RequestMethod.GET)
    public String task(@PathVariable("id") Long id, Model model) {
        model.addAttribute("task", taskService.findOne(id));
        return "task-ld/task";
    }
}
