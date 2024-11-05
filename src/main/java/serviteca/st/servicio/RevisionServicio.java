package serviteca.st.servicio;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import serviteca.st.modelo.Revision;
import serviteca.st.repositorio.RevisionRepositorio;
import serviteca.st.utils.GlobalConstants;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

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
        return revisionRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Revision no encontrado"));
    }

    public Revision guardarRevision(Revision revision) {
        return revisionRepositorio.save(revision);
    }

    // Cargar hasta 5 imágenes
    public List<String> uploadFotos(String id, MultipartFile[] files) {
        if (files.length != 5) {
            throw new IllegalArgumentException("Debe cargar exactamente 5 imágenes");
        }

        log.info("Cargando imágenes para la revisión con ID: {}", id);
        Revision revision = getRevision(id);

        List<String> fotoUrls = new ArrayList<>();

        // Procesar cada archivo y asignarlo a su campo correspondiente
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String fotoUrl = fotoFuncional.apply(id, file);
            fotoUrls.add(fotoUrl);

            switch (i) {
                case 0:
                    revision.setImgFrontal(fotoUrl);
                    break;
                case 1:
                    revision.setImgBack(fotoUrl);
                    break;
                case 2:
                    revision.setImgRight(fotoUrl);
                    break;
                case 3:
                    revision.setImgLeft(fotoUrl);
                    break;
                case 4:
                    revision.setImgIndicador(fotoUrl);
                    break;
                default:
                    throw new IllegalStateException("Número inesperado de imágenes");
            }
        }

        // Guardar los cambios en la base de datos
        revisionRepositorio.save(revision);

        return fotoUrls;
    }

    private final Function<String, String> fileExtension = filename -> Optional.of(filename)
            .filter(name -> name.contains("."))
            .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1))
            .orElse(".png");

    private final BiFunction<String, MultipartFile, String> fotoFuncional = (id, image) -> {
        String filename = id + "_" + System.currentTimeMillis() + fileExtension.apply(image.getOriginalFilename());

        try {
            Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if (!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);
            }
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename), REPLACE_EXISTING);
            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/serviteca/revisiones/image/" + filename)
                    .toUriString();
        } catch (Exception exception) {
            log.error("Error al guardar la imagen: ", exception);
            throw new RuntimeException("No se puede guardar la imagen: " + exception.getMessage(), exception);
        }
    };

    public void update(String id, Revision object) throws Exception {
        Optional<Revision> optionalEntity = revisionRepositorio.findById(id);

        if (optionalEntity.isEmpty()) {
            throw new Exception("No se encontró registro");
        }

        Revision entityToUpdate = optionalEntity.get();
        BeanUtils.copyProperties(object, entityToUpdate, GlobalConstants.EXCLUDED_FIELDS.toArray(new String[0]));

        revisionRepositorio.save(entityToUpdate);
    }

}
