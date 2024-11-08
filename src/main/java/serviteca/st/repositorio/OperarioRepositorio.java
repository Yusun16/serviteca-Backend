package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import serviteca.st.modelo.Dto.InfoOperarioDto;
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

    @Query(value = "SELECT   " +
            "    operario.cedula,   " +
            "    CONCAT(operario.nombre, ' ', operario.apellido) AS nombreCompleto,   " +
            "    COUNT(orden.servicio_id) AS totalServiciosRealizados,   " +
            "    servicio.porcentaje_operario AS porcentajeOperario,  " +
            "    servicio.valor_servicio AS valorServicio  " +
            "FROM operario  " +
            "INNER JOIN orden ON operario.id = orden.operario_id  " +
            "INNER JOIN servicio ON orden.servicio_id = servicio.id_servicio  " +
            "WHERE  " +
            "(:anio IS NULL OR YEAR(orden.fecha) = :anio) AND  " +
            "(:mes IS NULL OR MONTH(orden.fecha) = :mes) AND  " +
            "(:cedula IS NULL OR operario.cedula = :cedula)  " +
            "GROUP BY   " +
            "    operario.cedula,   " +
            "    operario.nombre,   " +
            "    operario.apellido,   " +
            "    servicio.porcentaje_operario,  " +
            "    servicio.valor_servicio;", nativeQuery = true)
    List<InfoOperarioDto> consultarInformeOperario(String anio,String mes, String cedula);
}
