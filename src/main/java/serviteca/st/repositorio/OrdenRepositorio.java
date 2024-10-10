package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import serviteca.st.modelo.Dto.vehiculosClientes;
import serviteca.st.modelo.Orden;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrdenRepositorio extends JpaRepository<Orden, Integer> {

    @Query(value="select codigo from orden where id_orden=(select max(id_orden) from orden);",nativeQuery = true)
    String buscarCodigo();

    @Query(value = " select o.* " +
            " from orden o " +
            " left join cliente c on o.cliente_id = c.id " +
            " where (:fecha is null or o.fecha = :fecha) " +
            " and (:idOrden is null or o.id_orden = :idOrden) " +
            " and (:nombreCliente is null or c.nombre = :nombreCliente) " +
            " and (:placaVehiculo is null or o.placa_vehiculo = :placaVehiculo) ", nativeQuery = true)
    List<Orden> findByIdOrdenOrClienteAndPlacaVehiculoOrFecha(
            Integer idOrden, String nombreCliente, String placaVehiculo, LocalDate fecha
    );


    @Query(value = "select  " +
            "v.id as vehiculoId, " +
            "v.placa, " +
            "concat(c.nombre, c.apellido) as nombreCliente " +
            " from cliente c " +
            " inner join vehiculo v on c.id = v.cliente_id " +
            " where c.id = :clienteId", nativeQuery = true)
    List<vehiculosClientes>  findVehiculosClientes(Integer clienteId);

}
