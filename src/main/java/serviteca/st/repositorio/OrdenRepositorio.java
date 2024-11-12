package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import serviteca.st.modelo.Dto.EjecucionServicioDto;
import serviteca.st.modelo.Dto.HistoricoVehiculoDto;
import serviteca.st.modelo.Dto.buscarOrdenEspecificaDto;
import serviteca.st.modelo.Dto.vehiculosClientes;
import serviteca.st.modelo.Orden;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrdenRepositorio extends JpaRepository<Orden, Integer> {

    @Query(value="select codigo from orden where id_orden=(select max(id_orden) from orden);",nativeQuery = true)
    String buscarCodigo();

    @Query(value = " select   " +
            "o.id_orden AS idOrden,  " +
            "o.fecha,  " +
            "o.kilometraje,  " +
            "o.codigo,  " +
            "o.observaciones,  " +
            "concat(op.nombre, ' ', op.apellido) AS nombreOperario  " +
            "from orden o  " +
            "inner join operario op on o.operario_id = op.id  " +
            "inner join vehiculo v on o.vehiculo_id = v.id  " +
            "inner join cliente c on v.cliente_id = c.id  " +
            "where   " +
            "(:codigo is null or o.codigo = :codigo) AND  " +
            "(:nombreCliente is null or concat(c.nombre, ' ', c.apellido) = :nombreCliente) AND  " +
            "(:fecha is null or o.fecha = :fecha) AND  " +
            "(:placa is null or v.placa = :placa) ", nativeQuery = true)
    List<buscarOrdenEspecificaDto> buscarOrdenEspecifica(
            String codigo, String nombreCliente, LocalDate fecha, String placa
    );


    @Query(value = "select  " +
            "v.id as vehiculoId, " +
            "v.placa, " +
            "concat(c.nombre, c.apellido) as nombreCliente " +
            " from cliente c " +
            " inner join vehiculo v on c.id = v.cliente_id " +
            " where c.id = :clienteId", nativeQuery = true)
    List<vehiculosClientes>  findVehiculosClientes(Integer clienteId);

    @Query(value = "select  " +
            "vehiculo.placa as placaVehiculo,  " +
            "orden.codigo as codigoOrden,  " +
            "orden.fecha as fechaOrden, " +
            "orden.hora as horaOrden, " +
            "orden.id_orden as idOrden,"+
            "revision.img_frontal as imgFrontalRevision,  " +
            "revision.img_back as imgBackRevision, " +
            "revision.id as idRevision,"+
            "servicio.nombre as nombreServicio, " +
            "orden.operario_id as operarioId, " +
            "orden.estado, " +
            "orden.fecha_fin as fechaFinal, " +
            "orden.hora_fin as horaFinal,  " +
            "orden.observaciones as observacion, " +
            "revision.img_frontal_despues as imgFrontalDespues," +
            "revision.img_back_despues as imgPosteriorDespues " +
            "from orden  " +
            "inner join vehiculo on orden.vehiculo_id = vehiculo.id " +
            "inner join revision on orden.id_orden = revision.orden_id_orden " +
            "inner join servicio on orden.servicio_id = servicio.id_servicio " +
            "where orden.id_orden = :idOrden", nativeQuery = true)
            List<EjecucionServicioDto>  findejecucionservicio(Integer idOrden);


    @Query(value = "SELECT " +
            "   o.id_orden as idOrden,  " +
            "    o.fecha AS fechaOrden, " +
            "    o.kilometraje AS kilometros, " +
            "    CONCAT(op.nombre, ' ', op.apellido) AS nombreOperario, " +
            "    o.observaciones AS observacion " +
            "FROM  " +
            "    serviteca.orden o " +
            "JOIN  " +
            "    serviteca.vehiculo v ON o.vehiculo_id = v.id " +
            "JOIN  " +
            "    serviteca.operario op ON o.operario_id = op.id " +
            "WHERE  " +
            "    o.vehiculo_id = :vehiculoId;", nativeQuery = true)
    List<HistoricoVehiculoDto> consultarHistoricoVehiculo(Integer vehiculoId);
}
