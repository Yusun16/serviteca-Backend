package serviteca.st.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serviteca.st.entity.FileEntity;

import java.util.UUID;

@Repository

public interface FileRepository extends JpaRepository<FileEntity, UUID> {
}
