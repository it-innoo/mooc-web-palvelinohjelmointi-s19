package gifbin;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jukka Ahlgren <jukka.ahlgren at gmail.com>
 */
public interface GifRepository extends JpaRepository<GifImage, Long>{
    
}
