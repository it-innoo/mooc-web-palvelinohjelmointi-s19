package todoapplication;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Jukka Ahlgren <jukka.ahlgren at gmail.com>
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item extends AbstractPersistable<Long> {

    private String name;
    private int checked;

    public Item(String name) {
        this.name = name;
    }
    
}
