package thymeleafexamples.layouts.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {
    @RequestMapping("contact")
    public String contactForm() {
        return "home/contact :: form";
    }
}
