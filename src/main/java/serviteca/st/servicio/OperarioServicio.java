package serviteca.st.servicio;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import serviteca.st.modelo.Operario;
import serviteca.st.repositorio.OperarioRepositorio;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static serviteca.st.constant.Constant.PHOTO_DIRECTORY;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class OperarioServicio {

    private final OperarioRepositorio operarioRepositorio;

    public List<Operario> listarOperarios() {
        return operarioRepositorio.findAll();
    }

    public Operario getOperario(String id) {
        return operarioRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Operario no encontrado"));
    }

    public Operario buscarOperarioPorId(String id) {
        // Se hizo unos cambios!!!
        Operario operario = operarioRepositorio.findById(id).orElse(null);
        return operario;
    }

    public List<Operario> listarOperariosbyparams(String cedula, String correo, String telefono) {
        return operarioRepositorio.findByCedulaOrCorreoOrTelefono(cedula, correo, telefono);
    }

    public String buscarCedula() {
        String searchCedula = operarioRepositorio.buscarCedula();
        return searchCedula;
    }

    //Crear un operario
    public Operario guardarOperario(Operario operario) {
        return operarioRepositorio.save(operario);
    }

    // Eliminar operario
    public void eliminarOperario(Operario operario) {
        operarioRepositorio.delete(operario);
    }

    // Funcion de Servicio de foto I
    public String uploadFoto(String id, MultipartFile file) {
        log.info("Mensaje de foto para el usuario por ID: {}", id);
        Operario operario = getOperario(id);
        String fotoUrl = fotoFuncional.apply(id, file);
        operario.setFotoUrl(fotoUrl);
        operarioRepositorio.save(operario);
        return fotoUrl;
    }

    // Funcion de Servicio de foto III
    private final Function<String, String> fileExtension = filename -> Optional.of(filename).filter(name ->name.contains("."))
            .map(name -> "." + name.substring(filename.lastIndexOf(".") +1)).orElse(".png");

    // Funcion de Servicio de foto II
    private final BiFunction<String, MultipartFile, String> fotoFuncional = (id, image) -> {
        String filename = id + fileExtension.apply(image.getOriginalFilename());

        try {
            Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if(!Files.exists(fileStorageLocation)) { Files.createDirectories(fileStorageLocation); }
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename), REPLACE_EXISTING);
            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/serviteca/operarios/image/" + filename)
                    .toUriString();
        } catch (Exception exception) {
            log.error("Error al guardar la imagen: ", exception);
            throw new RuntimeException("No se puede guardar la imagen" + exception.getMessage(), exception);
        }
    };
}
