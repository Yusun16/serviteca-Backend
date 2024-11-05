package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import serviteca.st.modelo.OrdenAutoparte;

public interface OrdenAutoparteRepositorio extends JpaRepository<OrdenAutoparte, Integer> {
}
