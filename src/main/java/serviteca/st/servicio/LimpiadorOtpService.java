package serviteca.st.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import serviteca.st.repositorio.ForgotPasswordRepositorio;

import java.util.Date;

@Service
public class LimpiadorOtpService {

    @Autowired
    private ForgotPasswordRepositorio forgotPasswordRepositorio;

    @Scheduled(fixedRate = 60000)
    public void limpaOtp() {
        forgotPasswordRepositorio.findAll().forEach(fp -> {
            if (fp.getTiempoEspera().before(new Date())) {
                forgotPasswordRepositorio.delete(fp);
            }
        });
    }
}
