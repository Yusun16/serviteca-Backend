package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import serviteca.st.modelo.Cliente;
import serviteca.st.modelo.Operario;

import java.util.List;

public interface ClienteRepositorio extends JpaRepository<Cliente , Integer> {

    // Setencia SQL para buscar
    @Query(value = "select cedula from cliente where id=(select max(id_cliente) from cliente);", nativeQuery = true)
    String buscarCedula();

    List<Cliente> findByCedulaOrCorreoOrTelefono(
            String cedula, String correo, Double telefono
    );





}
