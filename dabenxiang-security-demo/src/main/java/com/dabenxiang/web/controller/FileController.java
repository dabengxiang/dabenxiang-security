package com.dabenxiang.web.controller;

import com.dabenxiang.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.ch.IOUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * Date:2018/7/29
 * Author: yc.guo the one whom in nengxun
 * Desc:
 */

@RequestMapping("/file")
@RestController
public class FileController {

    String folder = "D:/work/idea_workspace/dabenxiang-security/dabenxiang-security-demo/src/main/java/com/dabenxiang/web/controller";

    @PostMapping
    public FileInfo uploadFile(MultipartFile file)throws Exception{
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(folder, new Date().getTime() + ".txt");

        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());

    }



    @GetMapping
    public void downFile( String id, HttpServletRequest request, HttpServletResponse response) throws IOException {    
//    @GetMapping("/{id}")
//    public void downFile(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (
                FileInputStream fileInputStream = new FileInputStream(new File(folder, id + ".txt"));
                OutputStream outputStream = response.getOutputStream();
        ){
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=test.txt");
            IOUtils.copy(fileInputStream,outputStream);
            outputStream.flush();
        }



    }
}
