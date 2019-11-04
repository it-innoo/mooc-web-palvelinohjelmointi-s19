package persondatabase;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jukka Ahlgren <jukka.ahlgren at gmail.com>
 */
public interface PersonRepository extends JpaRepository<Person, Long>{
    
}
