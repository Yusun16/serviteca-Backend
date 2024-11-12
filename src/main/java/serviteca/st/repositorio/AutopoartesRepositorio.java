package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import serviteca.st.modelo.Autoparte;
import serviteca.st.modelo.Dto.InfoAutoparteDto;

import java.util.List;

public interface AutopoartesRepositorio extends JpaRepository<Autoparte, Integer> {

    //Sentencia SQL para buscar
    @Query(value = "select siigo from autoparte where id_aupartes=(select max(id_aupartes) from autoparte);", nativeQuery = true)
    String buscarCodigo();

    List<Autoparte> findBySiigoOrReferenciaOrDescripcion(
        String siigo, String referencia, String descripcion
    );

    @Query(value = "SELECT " +
            "    a.siigo AS codigo, " +
            "    a.descripcion AS descripcion, " +
            "    (a.cantidad - COALESCE((SELECT SUM(-oa.cantidad) " +
            "                            FROM `serviteca`.`orden_autoparte` oa " +
            "                            INNER JOIN `serviteca`.`orden` o ON oa.orden_id = o.id_orden " +
            "                            WHERE oa.autoparte_id = a.id_aupartes " +
            "                              AND (:anio IS NULL OR YEAR(o.fecha) = :anio) " +
            "                              AND (:mes IS NULL OR MONTH(o.fecha) = :mes)), 0)) AS cantidadExistente, " +
            "    ((a.cantidad - COALESCE((SELECT SUM(oa.cantidad) " +
            "                            FROM `serviteca`.`orden_autoparte` oa " +
            "                            INNER JOIN `serviteca`.`orden` o ON oa.orden_id = o.id_orden " +
            "                            WHERE oa.autoparte_id = a.id_aupartes " +
            "                              AND (:anio IS NULL OR YEAR(o.fecha) = :anio) " +
            "                              AND (:mes IS NULL OR MONTH(o.fecha) = :mes)), 0)) * a.precio_autoparte) AS saldo " +

            "FROM `serviteca`.`autoparte` a " +

            "WHERE " +
            "    (:codigo IS NULL OR a.siigo = :codigo);", nativeQuery = true)
    List<InfoAutoparteDto> informeAutoparte(String anio, String mes, String codigo);

}
