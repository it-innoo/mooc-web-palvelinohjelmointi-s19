package hellosession;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloSessionController {
    
    @RequestMapping("*")
    @ResponseBody
    public String sayHello(HttpSession session) {
        int visits = 0;
        String message = "Hello there!";

        if (session.getAttribute("count") != null) {
            visits = (int) session.getAttribute("count");
            message = "Hello again!";
        }

        visits++;
        session.setAttribute("count", visits);

        return message;
    }
}
