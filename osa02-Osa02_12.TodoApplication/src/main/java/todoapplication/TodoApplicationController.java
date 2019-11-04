package todoapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoApplicationController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", this.itemRepository.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String get(Model model, @PathVariable Long id) {
        Item todo = itemRepository.getOne(id);
        int checked = todo.getChecked();
        todo.setChecked(++checked);
        itemRepository.save(todo);
        model.addAttribute("item", itemRepository.getOne(id));
        return "todo";
    }

    @PostMapping("/")
    public String create(@RequestParam String name) {
        itemRepository.save(new Item(name));
        return "redirect:/";
    }
}
