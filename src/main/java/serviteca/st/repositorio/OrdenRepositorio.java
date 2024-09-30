package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import serviteca.st.modelo.Orden;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrdenRepositorio extends JpaRepository<Orden, Integer> {

    @Query(value="select codigo from orden where id_orden=(select max(id_orden) from orden);",nativeQuery = true)
    String buscarCodigo();

    @Query(value = "select o.* " +
            " from orden o " +
            " inner join cliente c on o.id_cliente = c.id  " +
            " where o.id_orden = :idOrden " +
            " OR c.nombre = :nombreCliente " +
            " OR o.placa_vehiculo = :placaVehiculo " +
            " OR o.fecha = :fecha", nativeQuery = true)
    List<Orden> findByIdOrdenOrClienteAndPlacaVehiculoOrFecha(
            Integer idOrden, String nombreCliente, String placaVehiculo, LocalDate fecha
    );

}
