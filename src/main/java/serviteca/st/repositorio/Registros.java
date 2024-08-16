package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import serviteca.st.modelo.Registrar_Operario;

public interface Registros  extends JpaRepository<Registrar_Operario, Integer> {
}
