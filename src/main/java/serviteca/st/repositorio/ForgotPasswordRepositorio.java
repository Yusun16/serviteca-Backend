package serviteca.st.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import serviteca.st.modelo.ForgotPassword;
import serviteca.st.modelo.User;

import java.util.Optional;

public interface ForgotPasswordRepositorio extends JpaRepository<ForgotPassword, Integer> {

    @Query("select id from ForgotPassword  id where id.otp = ?1 and id.user = ?2")
    Optional<ForgotPassword> findByOtpAndUsername(Integer  otp, User user);

    Optional<ForgotPassword> findByUser(User user);
}
