package reloadheroes;

import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReloadController {

    @Autowired
    private ReloadStatusRepository reloadStatusRepository;

    @Autowired
    private HttpSession session;

    @RequestMapping("*")
    public String reload(Model model) {

        ReloadStatus reloadStatus = new ReloadStatus(UUID.randomUUID().toString(), 0);
        if (session.getAttribute("name") != null) {
            String name = session.getAttribute("name").toString();
            reloadStatus = reloadStatusRepository.findByName(name);
        }

        model.addAttribute("status", new ReloadStatus());

        reloadStatus.setReloads(reloadStatus.getReloads() + 1);
        reloadStatusRepository.save(reloadStatus);

        session.setAttribute("name", reloadStatus.getName());
        model.addAttribute("status", reloadStatus);

        Pageable pageable = PageRequest.of(0, 5, Sort.by("name").descending());

        model.addAttribute("scores", reloadStatusRepository.findAll());
        return "index";
    }
}
