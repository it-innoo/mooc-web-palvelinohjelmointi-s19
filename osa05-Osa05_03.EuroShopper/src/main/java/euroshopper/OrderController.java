package euroshopper;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private HttpSession session;
    
    @RequestMapping("/orders")
    public String list(Model model) {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "name");
        model.addAttribute("orders", orderRepository.findAll(pageable));
        return "orders";
    }

    @PostMapping("/orders")
    public String order(@RequestParam String name, @RequestParam String address) {

        Order order = new Order();

        if (session.getAttribute("id") != null) {
            order = orderRepository.getOne(( Long )session.getAttribute("id"));
        }
        
        order.setName(name);
        order.setAddress(address);

        List<OrderItem> items = new ArrayList<>();

        for (Item item : shoppingCart.getItems().keySet()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(itemRepository.getOne(item.getId()));
            orderItem.setItemCount(shoppingCart.getItems().get(item));
            items.add(orderItem);
        }

        order.setOrderItems(items);
        orderRepository.save(order);
        shoppingCart.getItems().clear();

        return "redirect:/orders";
    }
}
