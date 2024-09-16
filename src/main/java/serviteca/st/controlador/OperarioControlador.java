package serviteca.st.controlador;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import serviteca.st.excepcion.RecursoNoEncontradoExcepcion;
import serviteca.st.modelo.Operario;
import serviteca.st.servicio.OperarioServicio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import static serviteca.st.constant.Constant.PHOTO_DIRECTORY;

@RestController
@RequestMapping("serviteca")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class OperarioControlador {
    private static final Logger logger = LoggerFactory.getLogger(OperarioControlador.class);

    private final OperarioServicio operarioServicio;

    @GetMapping("/operarios")
    public List<Operario> listarOperarios() {
        var operario = operarioServicio.listarOperarios();
        operario.forEach(ap -> logger.info(ap.toString()));
        return operario;
    }

    @PostMapping("/operarios")
    public ResponseEntity<Operario> crearOperario(@RequestBody Operario operario) {
        Operario nuevaOperario = operarioServicio.guardarOperario(operario);
        return ResponseEntity.ok(nuevaOperario);
    }

    @GetMapping("/operarios/{id}")
    public ResponseEntity<Operario> getOperario(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(operarioServicio.getOperario(id));
    }

    @PutMapping("/operarios/photo")
    public ResponseEntity<String> uploadFoto(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok().body(operarioServicio.uploadFoto(id, file));
    }

    @GetMapping(path = "/operarios/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getFotografia(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }

    // Eliminar el id
    @DeleteMapping("/operarios/{id}")
    public ResponseEntity<Map<String, Boolean>>eliminarOperario(@PathVariable String id) {
        Operario operario = operarioServicio.buscarOperarioPorId(id);
        if (operario == null)
            throw new RecursoNoEncontradoExcepcion("no se encontro el id:" + id);
        operarioServicio.eliminarOperario(operario);
        Map<String, Boolean> answer = new HashMap<>();
        answer.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(answer);
    }

    // Control de buscar. Buscar I
    @GetMapping("/operarios/buscar")
    public List<Operario> buscarOperariosPorItems(@RequestParam String cedula, @RequestParam String correo, @RequestParam String telefono) {
        return operarioServicio.listarOperariosbyparams(cedula, correo, telefono);
    }

    // Control de buscar. Buscar II
    @GetMapping("/busqueda-operario")
    public String buscarCedula() {
        return operarioServicio.buscarCedula();
    }
}
