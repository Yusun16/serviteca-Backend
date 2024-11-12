package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import serviteca.st.modelo.ERole;
import serviteca.st.modelo.Role;

import java.util.Optional;

@Repository
public interface RoleRepositorio extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(ERole name);
}
