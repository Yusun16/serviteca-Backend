package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import serviteca.st.modelo.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente , Integer> {
}
