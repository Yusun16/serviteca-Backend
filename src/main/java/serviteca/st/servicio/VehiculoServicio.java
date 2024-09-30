package serviteca.st.servicio;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import serviteca.st.modelo.Vehiculo;
import serviteca.st.repositorio.VehiculoRepositorio;

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
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
@Slf4j
public class VehiculoServicio{

    private static final Logger log = LoggerFactory.getLogger(VehiculoServicio.class);
    private final VehiculoRepositorio vehiculoRepositorio;

    public List<Vehiculo> listarVehiculo() {
        return vehiculoRepositorio.findAll();
    }


    public Vehiculo buscarVehiculoPorId(String id) {
        return vehiculoRepositorio.findById(id).orElse(null);
    }


    public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
        return vehiculoRepositorio.save(vehiculo);
    }


    public void eliminarVehiculo(Vehiculo vehiculo) {
        vehiculoRepositorio.delete(vehiculo);
    }

    public String uploadFoto(String id, MultipartFile file) {
        log.info("Subiendo foto para el vehiculo con ID: {}", id);
        Vehiculo vehiculo = buscarVehiculoPorId(id);
        String fotoUrl = fotoFuncional.apply(id, file);
        vehiculo.setFoto(fotoUrl);
        vehiculoRepositorio.save(vehiculo);
        return fotoUrl;
    }

    // Función para obtener la extensión del archivo
    private final Function<String, String> fileExtension = filename -> Optional.of(filename)
            .filter(name -> name.contains("."))
            .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1))
            .orElse(".png");

    // Función para subir la foto
    private final BiFunction<String, MultipartFile, String> fotoFuncional = (id, image) -> {
        String filename = id + fileExtension.apply(image.getOriginalFilename());

        try {
            Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if (!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);
            }
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename), REPLACE_EXISTING);
            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/serviteca/vehiculos/image/" + filename)
                    .toUriString();
        } catch (Exception exception) {
            log.error("Error al guardar la imagen: ", exception);
            throw new RuntimeException("No se puede guardar la imagen: " + exception.getMessage(), exception);
        }
    };
}
