package serviteca.st.servicio;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import serviteca.st.modelo.Revision;
import serviteca.st.repositorio.RevisionRepositorio;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static serviteca.st.constant.Constant.PHOTO_DIRECTORY;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class RevisionServicio {

    private final RevisionRepositorio revisionRepositorio;

    public List<Revision> listarRevision() {
        return revisionRepositorio.findAll();
    }

    public Revision getRevision(String id) {
        return revisionRepositorio.findById(id).orElseThrow(() ->new RuntimeException("Revision no encontrado"));
    }

//    public Revision guardarRevision(Revision revision) {
//        return revisionRepositorio.save(revision);
//    }
//
//    public void uploadFotosYObservaciones(String id, MultipartFile[] images, String[] observations) {
//        log.info("Mensaje de fotos para el usuario por ID: {}", id);
//        Revision revision = getRevision(id);
//
//        // Asignar URLs de imágenes
//        if (images != null) {
//            if (images.length > 0) revision.setImgFrontal(saveFile(images[0]));
//            if (images.length > 1) revision.setImgBack(saveFile(images[1]));
//            if (images.length > 2) revision.setImgRight(saveFile(images[2]));
//            if (images.length > 3) revision.setImgLeft(saveFile(images[3]));
//            if (images.length > 4) revision.setImgIndicador(saveFile(images[4]));
//        }
//
//        // Asignar observaciones
//        if (observations != null) {
//            if (observations.length > 0) revision.setObservationsFrontal(observations[0]);
//            if (observations.length > 1) revision.setObservationsBack(observations[1]);
//            if (observations.length > 2) revision.setObservationsRight(observations[2]);
//            if (observations.length > 3) revision.setObservationsLeft(observations[3]);
//            if (observations.length > 4) revision.setObservationsIndicador(observations[4]);
//        }
//
//        revisionRepositorio.save(revision);
//    }
//
//    private String saveFile(MultipartFile file) {
//        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//        Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
//        try {
//            if (!Files.exists(fileStorageLocation)) {
//                Files.createDirectories(fileStorageLocation);
//            }
//            Files.copy(file.getInputStream(), fileStorageLocation.resolve(filename), REPLACE_EXISTING);
//            return ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path("/serviteca/revision/image/" + filename)
//                    .toUriString();
//        } catch (Exception exception) {
//            log.error("Error al guardar la imagen: ", exception);
//            throw new RuntimeException("No se puede guardar la imagen: " + exception.getMessage(), exception);
//        }
//    }
}
