package todoapplication;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jukka Ahlgren <jukka.ahlgren at gmail.com>
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
    
}
