package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import serviteca.st.modelo.Cliente;

import java.util.List;

public interface ClienteRepositorio extends JpaRepository<Cliente , Integer> {

    @Query(value = "select cedula from cliente where id_cliente=(select max(id_cliente) from cliente);", nativeQuery = true)
    String buscarCedula();

    List<Cliente> findByCedulaOrCorreoOrTelefono(
            Integer cedula, String correo, Double telefono
    );
}
