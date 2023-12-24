package uz.pdp.online.springbootapplication.service.impl;

import org.springframework.stereotype.Service;
import uz.pdp.online.springbootapplication.entity.Upload;
import uz.pdp.online.springbootapplication.service.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import static uz.pdp.online.springbootapplication.utils.Utils.saveImage;
import static uz.pdp.online.springbootapplication.utils.Utils.uploadDir;

@Service
public class FileServiceImpl implements FileService {

    public Upload saveFile(String base64Image) {
        String filePath = saveImage(base64Image);

        if (!filePath.isEmpty()) {
            return createUploadObject(filePath);
        } else {
            return null;
        }
    }

    public String downloadFile(String fileName) {
        String filePath = Paths.get(uploadDir, fileName).toString();

        try {
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
            return "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            return "";
        }
    }

    private Upload createUploadObject(String filePath) {
        Path path = Paths.get(filePath);
        String originalName = path.getFileName().toString();
        String generatedName = path.getFileName().toString();
        long size = 0;
        String mimeType = "image/png";

        return Upload.builder()
                .originalName(originalName)
                .generatedName(generatedName)
                .size(size)
                .mimeType(mimeType)
                .uploadedPath(filePath)
                .build();
    }
}

