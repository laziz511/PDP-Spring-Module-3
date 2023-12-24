package uz.pdp.online.springbootapplication.service;

import uz.pdp.online.springbootapplication.entity.Upload;

public interface FileService {
    Upload saveFile(String base64Image);
    String downloadFile(String fileName);
}
