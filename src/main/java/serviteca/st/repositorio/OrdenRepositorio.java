package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import serviteca.st.modelo.Orden;

public interface OrdenRepositorio extends JpaRepository<Orden, Integer> {

}
