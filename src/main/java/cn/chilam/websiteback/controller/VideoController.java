package cn.chilam.websiteback.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @program: website-back
 * @description: 视频控制器
 * @author: chilam
 * @create: 2020-04-05 14:47
 **/
@RestController
public class VideoController {

    @PostMapping("/video")
    public void videoStream(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File("D:/Videos/Captures/WW3.mp4");
        FileInputStream fileInputStream = new FileInputStream(file);
        int i = fileInputStream.available();
        byte[] bytes = new byte[i];
        fileInputStream.read(bytes);
        response.setContentType("application/video");
        OutputStream output = response.getOutputStream();
        output.write(bytes);
        output.flush();
        output.close();
        fileInputStream.close();
    }



}
