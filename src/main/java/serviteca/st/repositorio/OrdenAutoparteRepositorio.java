package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import serviteca.st.modelo.Autoparte;
import serviteca.st.modelo.OrdenAutoparte;

import java.util.List;
import java.util.Optional;

public interface OrdenAutoparteRepositorio extends JpaRepository<OrdenAutoparte, Integer> {

    @Query(value = "select * from orden_autoparte where autoparte_id = :autoparteId and orden_id = :ordenId", nativeQuery = true)
    Optional<OrdenAutoparte> findByOrdenIdAndAutoparteId(Integer ordenId, Integer autoparteId);

    @Query(value = "select * from autoparte inner join orden_autoparte on autoparte.id_aupartes = orden_autoparte.autoparte_id where orden_autoparte.orden_id = :ordenId ", nativeQuery = true)
    List<Autoparte> buscarAutopartePorOrdenId(Integer ordenId);
}
