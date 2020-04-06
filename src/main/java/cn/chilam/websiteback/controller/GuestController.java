package cn.chilam.websiteback.controller;

import cn.chilam.websiteback.common.entity.ResultMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @program: website-back
 * @description: 游客权限api
 * @author: chilam
 * @create: 2020-04-01 21:11
 **/
@RestController
@RequestMapping("/guest")
public class GuestController {
    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public ResultMap login() {
        return ResultMap.ok();
    }

    @GetMapping("/video")
    public void videoStream(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File("D:/Videos/Captures/WW3.mp4");
        FileInputStream fileInputStream = new FileInputStream(file);
        int i = fileInputStream.available();
        byte[] bytes = new byte[i];
        fileInputStream.read(bytes);
        String rangeString = request.getHeader("Range");
        long range = Long.valueOf(rangeString.substring(rangeString.indexOf("=") + 1,
                rangeString.indexOf("-")));
        response.setHeader("Content-Type", "video/mp4");
        response.setContentType("application/video");
        OutputStream output = response.getOutputStream();
        output.write(bytes);
        output.flush();
        output.close();
        fileInputStream.close();

    }

    @PostMapping("/upload")
    public ResultMap upload(@RequestParam("file")MultipartFile file){
        if(file.isEmpty()){
            return ResultMap.error().message("上传失败");
        }
        String fileName = file.getOriginalFilename();
        String filePath = "D://Videos/Upload/";
        File dest= new File(filePath+fileName);
        try{
            file.transferTo(dest);
            return ResultMap.ok().message("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultMap.error().message("上传失败");
    }

}
