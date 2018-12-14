package com.yangliuxin.application;

import com.yangliuxin.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

	FileInfo save(MultipartFile file) throws IOException;

	void delete(String id);

}
