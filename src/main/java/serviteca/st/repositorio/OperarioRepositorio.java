package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import serviteca.st.modelo.Operario;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperarioRepositorio extends JpaRepository<Operario, String> {
    Optional<Operario> findById(String id);

    // Setencia SQL para buscar
    @Query(value = "select cedula from operario where id=(select max(id) from operario);", nativeQuery = true)
    String buscarCedula();

    List<Operario> findByCedulaOrCorreoOrTelefono(
            String cedula, String correo, String telefono
    );
}
