package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import serviteca.st.modelo.Autoparte;

import java.util.List;

public interface AutopoartesRepositorio extends JpaRepository<Autoparte, Integer> {

    //Sentencia SQL para buscar
    @Query(value = "select siigo from autoparte where id_aupartes=(select max(id_aupartes) from autoparte);", nativeQuery = true)
    String buscarCodigo();

    List<Autoparte> findBySiigoOrReferenciaOrDescripcion(
        String siigo, String referencia, String descripcion
    );
}
