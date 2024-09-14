package serviteca.st.service;

import org.springframework.web.multipart.MultipartFile;
import serviteca.st.entity.FileEntity;
import serviteca.st.response.ResponseFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileService {
        FileEntity store(MultipartFile file, String observacionLateralDerecho, String observacionLateralIzquierdo, String observacionFrontal, String observacionPosterior, String observacionIndicadorCombustible) throws IOException;
        Optional<FileEntity> getFile(UUID id) throws FileNotFoundException;
        List<ResponseFile> getAllFiles();
}
