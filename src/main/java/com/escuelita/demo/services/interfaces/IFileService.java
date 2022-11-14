package com.escuelita.demo.services.interfaces;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    String upload (MultipartFile file);
}
