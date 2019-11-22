package euroshopper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jukka Ahlgren <jukka.ahlgren at gmail.com>
 */
@Data
@NoArgsConstructor
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart implements Serializable {

    private final Map<Item, Long> cart = new HashMap<>();
    
    public Map<Item, Long> getItems() {
        return cart;
    }

    public void addToCart(Item item) {
        cart.put(item, cart.getOrDefault(item, 0L) + 1L);
    }

    public double getSum() {

        return cart.keySet().stream()
            .map((item) -> item.getPrice() * cart.get(item))
            .reduce(0.0, (sumSoFar, cost) -> sumSoFar + cost);

    }
}
