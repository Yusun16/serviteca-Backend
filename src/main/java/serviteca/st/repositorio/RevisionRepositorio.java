package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serviteca.st.modelo.Revision;

import java.util.Optional;

@Repository
public interface RevisionRepositorio extends JpaRepository<Revision, String> {
    Optional<Revision> findById(String id);
}
