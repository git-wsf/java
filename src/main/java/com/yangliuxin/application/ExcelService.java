package com.yangliuxin.application;

import com.yangliuxin.domain.Excel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExcelService {


    Excel save(MultipartFile file) throws IOException;

    void delete(String id);



}
