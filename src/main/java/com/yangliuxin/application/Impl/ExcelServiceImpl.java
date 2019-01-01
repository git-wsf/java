package com.yangliuxin.application.Impl;

import com.yangliuxin.application.ExcelService;
import com.yangliuxin.domain.Excel;
import com.yangliuxin.domain.Shop;
import com.yangliuxin.repository.ExcelRepository;
import com.yangliuxin.repository.ShopRepository;
import com.yangliuxin.utils.FileUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ExcelServiceImpl implements ExcelService {
    
    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Value("${files.path}")
    private String filesPath;
    @Autowired
    private ExcelRepository excelRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Override
    public Excel save(MultipartFile file) throws IOException {
        String fileOrigName = file.getOriginalFilename();
        if (!fileOrigName.contains(".xlsx")) {
            throw new IllegalArgumentException("错误的文件格式，请上传xlsx格式的excel文档");
        }

        String md5 = FileUtil.fileMd5(file.getInputStream());
        Excel excel = excelRepository.getById(md5);
        if (excel != null) {
            excelRepository.update(excel);
            return excel;
        }

        fileOrigName = fileOrigName.substring(fileOrigName.lastIndexOf("."));
        String pathname = FileUtil.getPath() + md5 + fileOrigName;
        String fullPath = filesPath + pathname;
        FileUtil.saveFile(file, fullPath);

        long size = file.getSize();
        String contentType = file.getContentType();

        excel = new Excel();
        excel.setId(md5);
        excel.setContentType(contentType);
        excel.setSize(size);
        excel.setPath(fullPath);
        excel.setUrl(pathname);
        excel.setType(contentType.startsWith("image/") ? 1 : 0);

        excelRepository.save(excel);
        //导入
        List<Shop> shopList = new ArrayList<>();
        XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(ResourceUtils.getFile(excel.getPath())));
        XSSFSheet sheet = book.getSheetAt(0);

        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            Shop shop = new Shop();
            XSSFRow row = sheet.getRow(i);
            row.getCell(0).setCellType(CellType.STRING);
            row.getCell(5).setCellType(CellType.STRING);
            row.getCell(6).setCellType(CellType.STRING);
            row.getCell(7).setCellType(CellType.STRING);
            row.getCell(8).setCellType(CellType.STRING);
            row.getCell(9).setCellType(CellType.STRING);
            row.getCell(10).setCellType(CellType.STRING);
            row.getCell(11).setCellType(CellType.STRING);
            row.getCell(12).setCellType(CellType.STRING);
            row.getCell(13).setCellType(CellType.STRING);
            row.getCell(14).setCellType(CellType.STRING);
            row.getCell(15).setCellType(CellType.STRING);
            shop.setShopId(row.getCell(0).getStringCellValue());
            shop.setShopName(row.getCell(1).getStringCellValue());
            shop.setLevel(row.getCell(2).getStringCellValue());
            shop.setAddress(row.getCell(3).getStringCellValue());
            shop.setProvince(row.getCell(4).getStringCellValue());
            shop.setDay(Double.valueOf(row.getCell(5).getStringCellValue()).intValue());
            shop.setDayCountryCount(Integer.parseInt(row.getCell(6).getStringCellValue()));
            shop.setDayProvinceCount(Integer.parseInt(row.getCell(7).getStringCellValue()));
            shop.setWeek(Double.valueOf(row.getCell(8).getStringCellValue()).intValue());
            shop.setWeekCountryCount(Integer.parseInt(row.getCell(9).getStringCellValue()));
            shop.setWeekProvinceCount(Integer.parseInt(row.getCell(10).getStringCellValue()));
            shop.setSpring(Double.valueOf(row.getCell(11).getStringCellValue()).intValue());
            shop.setSpringCountryCount(Integer.parseInt(row.getCell(12).getStringCellValue()));
            shop.setSpringProvinceCount(Integer.parseInt(row.getCell(13).getStringCellValue()));
            shop.setPercent(String.format("%.2f", Double.valueOf(row.getCell(14).getStringCellValue())*100)+"%");
            shop.setDdd(row.getCell(15).getStringCellValue());

            shopList.add(shopRepository.save(shop));
        }

        log.debug("导入文件{}", fullPath);

        return excel;

    }

    @Override
    public void delete(String id) {
        Excel excel = excelRepository.getById(id);
        if (excel != null) {
            String fullPath = excel.getPath();
            FileUtil.deleteFile(fullPath);

            excelRepository.delete(id);
            log.debug("删除文件：{}", excel.getPath());
        }
    }
}
