package serviteca.st.controlador;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import serviteca.st.modelo.Revision;
import serviteca.st.servicio.RevisionServicio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

    @GetMapping("/revision")
    public List<Revision> listarRevision() {
        var revision = revisionServicio.listarRevision();
        revision.forEach(ap -> logger.info(ap.toString()));
        return revision;
    }

//    @PostMapping("/revision")
//    public ResponseEntity<Revision> crearRevision(@RequestParam(value = "observationsFrontal", required = false) String observationsFrontal,
//                                                  @RequestParam(value = "observationsBack", required = false) String observationsBack,
//                                                  @RequestParam(value = "observationsRight", required = false) String observationsRight,
//                                                  @RequestParam(value = "observationsLeft", required = false) String observationsLeft,
//                                                  @RequestParam(value = "observationsIndicador", required = false) String observationsIndicador,
//                                                  @RequestParam(value = "images", required = false) MultipartFile[] images) throws IOException {
//
//        Revision revision = new Revision();
//        revision.setObservationsFrontal(observationsFrontal);
//        revision.setObservationsBack(observationsBack);
//        revision.setObservationsRight(observationsRight);
//        revision.setObservationsLeft(observationsLeft);
//        revision.setObservationsIndicador(observationsIndicador);
//
//        revision = revisionServicio.guardarRevision(revision);
//
//        // Guardar imágenes y observaciones
//        revisionServicio.uploadFotosYObservaciones(revision.getId().toString(), images, new String[] {
//                observationsFrontal, observationsBack, observationsRight, observationsLeft, observationsIndicador
//        });
//
//        return ResponseEntity.ok(revision);
//    }

    @GetMapping("/revision/{id}")
    public ResponseEntity<Revision> getRevision(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(revisionServicio.getRevision(id));
    }

//    @PutMapping("/revision/photo")
//    public ResponseEntity<String> uploadFotosYObservaciones(
//            @RequestParam("id") String id,
//            @RequestParam(value = "images", required = false) MultipartFile[] images,
//            @RequestParam("observationsFrontal") String observationsFrontal,
//            @RequestParam("observationsBack") String observationsBack,
//            @RequestParam("observationsRight") String observationsRight,
//            @RequestParam("observationsLeft") String observationsLeft,
//            @RequestParam("observationsIndicador") String observationsIndicador
//    ) {
//        try {
//            // Llamar al servicio para guardar las imágenes y observaciones
//            revisionServicio.uploadFotosYObservaciones(id, images, new String[] {
//                    observationsFrontal, observationsBack, observationsRight, observationsLeft, observationsIndicador
//            });
//
//            return ResponseEntity.ok("Fotos y observaciones actualizadas con éxito.");
//        } catch (Exception e) {
//            // Manejo de errores
//            return ResponseEntity.status(500).body("Error al actualizar fotos y observaciones: " + e.getMessage());
//        }
//    }
//
//    @GetMapping(path = "/revision/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
//    public ResponseEntity<byte[]> getFotografia(@PathVariable("filename") String filename) throws IOException {
//        Path path = Paths.get(PHOTO_DIRECTORY + filename);
//        byte[] imageBytes = Files.readAllBytes(path);
//        return ResponseEntity.ok(imageBytes);
//    }
}
