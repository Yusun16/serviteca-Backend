package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import serviteca.st.modelo.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServicioRepositorio extends JpaRepository<Servicio, Integer> {
    List<Servicio> findByIdServicio(Integer idServicio);
    List<Servicio> findByDescripcionContaining(String descripcion);
    List<Servicio> findByIdServicioAndDescripcionContaining(Integer idServicio, String descripcion);
    Optional<Servicio> findTopByOrderByIdServicioDesc(); // Nuevo método
}
