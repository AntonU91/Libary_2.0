package second_project.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {


    @GetMapping
    public String greeting() {
        return "front_page";
    }
}





