package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import serviteca.st.modelo.Autoparte;
import serviteca.st.modelo.Dto.AutoparteDto;
import serviteca.st.modelo.OrdenAutoparte;

import java.util.List;
import java.util.Optional;

public interface OrdenAutoparteRepositorio extends JpaRepository<OrdenAutoparte, Integer> {

    @Query(value = "select * from orden_autoparte where autoparte_id = :autoparteId and orden_id = :ordenId", nativeQuery = true)
    Optional<OrdenAutoparte> findByOrdenIdAndAutoparteId(Integer ordenId, Integer autoparteId);

    @Query(value = "SELECT a.* FROM autoparte a INNER JOIN orden_autoparte oa ON a.id_aupartes = oa.autoparte_id WHERE oa.orden_id = :ordenId", nativeQuery = true)
    List<AutoparteDto> buscarAutopartePorOrdenId(@Param("ordenId") Integer ordenId);

}
