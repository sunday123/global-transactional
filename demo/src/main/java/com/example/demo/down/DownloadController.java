package com.example.demo.down;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

/**
 * Description Controller
 * Date 2021/9/5
 * Created by www.ij34.com
 */
@RestController
@RequestMapping("/")
public class DownloadController {


    @PostMapping("/download")
    public void downloadExcel(String name, HttpServletResponse response) {
        System.out.println(name+":name");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=test-"+ DateUtil.format(DateUtil.date(),"yyyy-MM-dd-hhMM-sss") +".xlsx");
//        ByteArrayInputStream stream = ExcelWriter.
//        IOUtils.copy(stream, response.getOutputStream());

        List<ExcelDto> list = ListUtil.of(new ExcelDto("aaa","bbb"));
        ExcelWriter writer = ExcelUtil.getBigWriter();
        writer.addHeaderAlias("a","a列");
        writer.addHeaderAlias("b","b列");

//一次性写出内容，使用默认样式，强制输出标题
        writer.write(list,true);
//        ByteArrayOutputStream outputStream= new ByteArrayOutputStream();
        try {
            writer.flush(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            writer.close();
        }

    }



}