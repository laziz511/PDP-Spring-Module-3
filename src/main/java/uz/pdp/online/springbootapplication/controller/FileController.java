package uz.pdp.online.springbootapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.springbootapplication.entity.Upload;
import uz.pdp.online.springbootapplication.service.FileService;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<Upload> uploadFile(@RequestBody String base64Image) {
        Upload upload = fileService.saveFile(base64Image);
        return ResponseEntity.status(HttpStatus.CREATED).body(upload);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<String> downloadFile(@PathVariable String fileName) {
        String file = fileService.downloadFile(fileName);
        return ResponseEntity.ok(file);
    }
}

