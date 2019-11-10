package examsandquestions;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jukka Ahlgren <jukka.ahlgren at gmail.com>
 */
public interface QuestionRepository extends JpaRepository<Question, Long>{
    
}
