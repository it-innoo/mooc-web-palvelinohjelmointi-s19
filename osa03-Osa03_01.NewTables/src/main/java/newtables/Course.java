package newtables;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Jukka Ahlgren <jukka.ahlgren at gmail.com>
 */
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Course extends AbstractPersistable<Long> {
    private String name;
    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();
}
