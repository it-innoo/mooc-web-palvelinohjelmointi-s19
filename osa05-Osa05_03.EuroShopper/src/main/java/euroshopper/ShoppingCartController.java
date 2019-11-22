package euroshopper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Jukka Ahlgren <jukka.ahlgren at gmail.com>
 */
@Controller
public class ShoppingCartController {

    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    private ItemRepository itemRepository;


    @GetMapping("/cart")
    public String get(Model model) {
        model.addAttribute("items", shoppingCart.getItems());
        model.addAttribute("sum", shoppingCart.getSum());

        return "cart";
    }

    @PostMapping("/cart/items/{id}")
    public String add(@PathVariable Long id) {
        Item item = itemRepository.getOne(id);
        shoppingCart.addToCart(item);
        
        return "redirect:/cart";
    }
}
