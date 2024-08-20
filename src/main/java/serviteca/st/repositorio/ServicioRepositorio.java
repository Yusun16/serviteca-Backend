package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import serviteca.st.modelo.Servicio;

public interface ServicioRepositorio extends JpaRepository<Servicio, Integer> {
}
