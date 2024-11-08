package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import serviteca.st.modelo.Dto.InfoServicioDto;
import serviteca.st.modelo.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServicioRepositorio extends JpaRepository<Servicio, Integer> {
    List<Servicio> findByIdServicio(Integer idServicio);
    List<Servicio> findByDescripcionContaining(String descripcion);
    List<Servicio> findByIdServicioAndDescripcionContaining(Integer idServicio, String descripcion);
    Optional<Servicio> findTopByOrderByIdServicioDesc(); // Nuevo método

    @Query(value = "SELECT servicio.codigo, servicio.descripcion, COUNT(servicio.codigo) AS cantidad, SUM(servicio.valor_servicio) AS valorTotal " +
            "FROM servicio " +
            "INNER JOIN orden ON servicio.id_servicio = orden.servicio_id " +
            "WHERE " +
            "(:anio IS NULL OR YEAR(orden.fecha) = :anio) AND " +
            "(:mes IS NULL OR MONTH(orden.fecha) = :mes) AND " +
            "(:codigo IS NULL OR servicio.codigo = :codigo) " +
            "GROUP BY servicio.codigo, servicio.descripcion " +
            "ORDER BY cantidad DESC;", nativeQuery = true)
    List<InfoServicioDto> buscarInformeServicio(String anio, String mes, String codigo);
}
