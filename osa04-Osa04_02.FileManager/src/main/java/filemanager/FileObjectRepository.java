package filemanager;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jukka Ahlgren <jukka.ahlgren at gmail.com>
 */
public interface FileObjectRepository extends JpaRepository<FileObject, Long>{
    
}
