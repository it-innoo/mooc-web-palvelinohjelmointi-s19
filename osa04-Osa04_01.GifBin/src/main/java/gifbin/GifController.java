package gifbin;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
/**
 *
 * @author Jukka Ahlgren <jukka.ahlgren at gmail.com>
 */
public class GifController {

    @Autowired
    private GifRepository gifRepository;

    @GetMapping("/gifs")
    public String gifs() {
        return "redirect:/gifs/1";
    }

    @GetMapping("/gifs/{id}")
    public String gifGet(Model model, @PathVariable Long id) {
        model.addAttribute("count", gifRepository.count());

        Long current = gifRepository.existsById(id) ? id : null;
        Long previous = gifRepository.existsById(id - 1) ? id - 1 : null;
        Long next = gifRepository.existsById(id + 1) ? id + 1 : null;

        model.addAttribute("current", current);
        model.addAttribute("previous", previous);
        model.addAttribute("next", next);

        return "gifs";
    }

    @PostMapping("/gifs")
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
        GifImage gif = new GifImage();
        gif.setImage(file.getBytes());
        if (file.getContentType().equals("image/gif")) {
            gifRepository.save(gif);
        }

        return "redirect:/gifs";
    }

    @GetMapping(path = "/gifs/{id}/content", produces = "image/gif")
    @ResponseBody
    public byte[] content(@PathVariable Long id) {
        return gifRepository.getOne(id).getImage();
    }
}
