package serviteca.st.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import serviteca.st.entity.FileEntity;
import serviteca.st.response.ResponseFile;
import serviteca.st.response.ResponseMessage;
import serviteca.st.service.FileService;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fileManager")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/uploadMultiple")
    public ResponseEntity<ResponseMessage> uploadFiles(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("observacionLateralDerecho") String observacionLateralDerecho,
            @RequestParam("observacionLateralIzquierdo") String observacionLateralIzquierdo,
            @RequestParam("observacionFrontal") String observacionFrontal,
            @RequestParam("observacionPosterior") String observacionPosterior,
            @RequestParam("observacionIndicadorCombustible") String observacionIndicadorCombustible) {
        String message = "";
        List<String> fileNames = new ArrayList<>();

        if (files.length == 0) {
            message = "No se seleccionaron archivos.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
        }

        try {
            for (MultipartFile file : files) {
                FileEntity fileEntity = fileService.store(file, observacionLateralDerecho, observacionLateralIzquierdo, observacionFrontal, observacionPosterior, observacionIndicadorCombustible);
                fileNames.add(fileEntity.getName());
            }

            int fileCount = fileNames.size();
            message = "Archivos subidos con éxito: " + fileCount + " archivos.";

            // Opcionalmente puedes listar los nombres de los archivos también
            // message += " " + String.join(", ", fileNames);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "No se pudieron subir los archivos!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable UUID id) throws FileNotFoundException {
        FileEntity fileEntity = fileService.getFile(id).orElseThrow(() -> new FileNotFoundException("Archivo no encontrado"));
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                .body(fileEntity.getData());
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getAllFiles() {
        List<ResponseFile> files = fileService.getAllFiles();
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
}
