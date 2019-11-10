package examsandquestions;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jukka Ahlgren <jukka.ahlgren at gmail.com>
 */
public interface ExamRepository  extends JpaRepository<Exam, Long>{
    @EntityGraph(attributePaths = {"questions"})
    List<Exam> findAll();
}
