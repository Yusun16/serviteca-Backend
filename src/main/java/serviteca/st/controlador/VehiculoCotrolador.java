package serviteca.st.controlador;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import serviteca.st.modelo.Vehiculo;
import serviteca.st.servicio.VehiculoServicio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import static serviteca.st.constant.Constant.PHOTO_DIRECTORY;

@RestController
@RequestMapping("serviteca")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
class vehiculoControlador {

    private static final Logger logger = LoggerFactory.getLogger(vehiculoControlador.class);

    private final VehiculoServicio vehiculoServicio;

    @GetMapping("/vehiculos")
    public List<Vehiculo> obtenerVehiculos() {
        var vehiculo = vehiculoServicio.listarVehiculo();
        vehiculo.forEach(ap -> logger.info(ap.toString()));
        return vehiculo;
    }

    @PostMapping("/vehiculos")
    public Vehiculo agregarVehiculo(@RequestBody Vehiculo vehiculo) {
        logger.info("Vehiculo a agregar: " + vehiculo);
        return vehiculoServicio.guardarVehiculo(vehiculo);
    }

    @PutMapping("/vehiculos/photo")
    public ResponseEntity<String> uploadFoto(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok().body(vehiculoServicio.uploadFoto(id, file));
    }

    @GetMapping(path = "/vehiculos/image/{filename}", produces = {IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE})
    public byte[] getFotografia(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }
}
