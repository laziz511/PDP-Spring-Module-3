package uz.pdp.online.springbootapplication.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Component
@Slf4j
public class Utils {
    public static final String uploadDir = "C:\\\\Users\\\\Hp\\\\images";

    private Utils() {

    }

    public static String saveImage(String base64Image) {
        if (base64Image.startsWith("data:image")) {
            String[] base64Arr = base64Image.split(",");
            if (base64Arr.length == 2) {
                byte[] image = Base64.getDecoder().decode(base64Arr[1]);
                String[] extensionArr = base64Arr[0].split(";");
                if (extensionArr.length == 2) {
                    extensionArr = extensionArr[0].split("/");
                    if (extensionArr.length == 2) {
                        String fileName = String.join("", UUID.randomUUID().toString(), ".", extensionArr[1]);
                        return saveFileToServer(new ByteArrayInputStream(image), fileName);
                    }
                }
            }
        }
        return "";
    }

    private static String saveFileToServer(ByteArrayInputStream inputStream, String fileName) {
        try {
            String filePath = Paths.get(uploadDir, fileName).toString();
            File file = new File(filePath);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }

            try (FileOutputStream fos = new FileOutputStream(file)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }

                return filePath;
            }
        } catch (IOException e) {
            log.warn("Error occurred while saving images to file system", e);
            return "";
        }
    }

    public static void deleteImageFromFileSystem(String imageUrl) {
        try {
            Path imagePath = Paths.get(imageUrl);

            if (Files.exists(imagePath)) {
                Files.delete(imagePath);
                log.info("Deleted image from file system: {}", imageUrl);
            } else {
                log.warn("Image not found in file system: {}", imageUrl);
            }
        } catch (IOException e) {
            log.warn("Error occurred while deleting image from file system", e);
        }
    }

}
