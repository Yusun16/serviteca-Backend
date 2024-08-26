package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import serviteca.st.modelo.Orden;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrdenRepositorio extends JpaRepository<Orden, Integer> {

    @Query(value="select codigo from orden where id_orden=(select max(id_orden) from orden);",nativeQuery = true)
    String buscarCodigo();

    List<Orden> findByIdOrdenOrClienteAndPlacaVehiculoOrFecha(
            Integer idOrden, String cliente, String placaVehiculo, Date fecha
    );

}
