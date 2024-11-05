package serviteca.st.controlador;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import serviteca.st.modelo.Dto.ApiResponseDto;
import serviteca.st.modelo.Revision;
import serviteca.st.servicio.RevisionServicio;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import static serviteca.st.constant.Constant.PHOTO_DIRECTORY;

@RestController
@RequestMapping("serviteca")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class RevisionControlador {

    private static final Logger logger = LoggerFactory.getLogger(RevisionControlador.class);

    private final RevisionServicio revisionServicio;

    @GetMapping("/revisiones")
    public List<Revision> listarRevision() {
        var revision = revisionServicio.listarRevision();
        revision.forEach(ap -> logger.info(ap.toString()));
        return revision;
    }

    @GetMapping("/revisiones/{id}")
    public ResponseEntity<Revision> getRevision(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(revisionServicio.getRevision(id));
    }

    @PostMapping("/revisiones")
    public ResponseEntity<Revision> crearRevision(@RequestBody Revision revision) {
        Revision nuevoRevision = revisionServicio.guardarRevision(revision);
        return ResponseEntity.ok(nuevoRevision);
    }

    @PutMapping("/revisiones/uploadFotos")
    public ResponseEntity<List<String>> uploadFotos(@RequestParam("id") String id, @RequestParam("files") MultipartFile[] files) {
        List<String> fotoUrls = revisionServicio.uploadFotos(id, files);
        return ResponseEntity.ok().body(fotoUrls);
    }

    @GetMapping(path = "/revisiones/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getFotografia(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }

    @PutMapping("/revisiones/{id}")
    public ResponseEntity<ApiResponseDto<Revision>> update(@PathVariable String id, @RequestBody Revision object) {
        try {
            revisionServicio.update(id, object);
            return ResponseEntity.ok(new ApiResponseDto<>("Datos actualizados", null, true));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiResponseDto<Revision>(e.getMessage(), null, false));
        }
    }


}