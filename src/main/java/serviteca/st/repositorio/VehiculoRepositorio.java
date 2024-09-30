package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import serviteca.st.modelo.Vehiculo;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculoRepositorio extends JpaRepository<Vehiculo, String> {
    Optional<Vehiculo> findById(String id);

    // Setencia SQL para buscar
    @Query(value = "select placa from vehiculo where id=(select max(id) from vehiculo);", nativeQuery = true)
    String buscarPlaca();

    List<Vehiculo> findByPlacaOrObservacionOrModelo(
            String placa, String observacion, String modelo
    );


}
