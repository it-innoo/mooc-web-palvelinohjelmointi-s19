package hellopath;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloPathController {
/*
    @GetMapping("*")
    @ResponseBody
    public String home() {
        return "Hello World!";
    }
*/
    @GetMapping("/path")
    @ResponseBody
    public String path() {
        return "Oikein!";
    }
}
