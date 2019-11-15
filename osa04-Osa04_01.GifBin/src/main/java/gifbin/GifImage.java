package gifbin;

import javax.persistence.Entity;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Jukka Ahlgren <jukka.ahlgren at gmail.com>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GifImage extends AbstractPersistable<Long> {
    @Lob
    private byte[] image;
}
