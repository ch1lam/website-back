package cn.chilam.websiteback.controller;

import cn.chilam.websiteback.common.entity.ResultMap;
import cn.chilam.websiteback.mapper.VideoMapper;
import cn.chilam.websiteback.service.FileService;
import cn.chilam.websiteback.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @program: website-back
 * @description: 游客权限api
 * @author: chilam
 * @create: 2020-04-01 21:11
 **/
@RestController
@RequestMapping("/guest")
@CrossOrigin
public class GuestController {


    @Autowired
    FileService fileService;
    @Autowired
    VideoService videoService;

    // 播放视频
    @GetMapping("/video")
    public void videoStream(HttpServletRequest request,
                            HttpServletResponse response,
                            @RequestParam("id") Integer id) throws IOException {
        // 清空缓存
        response.reset();
        // 获取响应的输出流
        OutputStream outputStream = response.getOutputStream();
//        File file = new File("D:/Videos/Captures/WW3.mp4");
        File file = new File(videoService.getUrlById(id));
//        File file = new File("D://Videos/Captures/WW3.mp4");
        if (file.exists()) {
            // 创建随机读取文件对象
            RandomAccessFile targetFile = new RandomAccessFile(file, "r");
            long fileLength = targetFile.length();
            // 获取从那个字节开始读取文件
            String rangeString = request.getHeader("Range");
            if (rangeString != null) {
                long range = Long.parseLong(rangeString.substring(rangeString.indexOf("=") + 1,
                        rangeString.indexOf("-")));
                // 设置内容类型
                response.setContentType("video/mp4");
                // 设置此次相应返回的数据长度
                response.setHeader("Content-Length", String.valueOf(fileLength - range));
                // 设置此次相应返回的数据范围
                response.setHeader("Content-Range", "bytes " + range + "-" + (fileLength - 1) +
                        "/" + fileLength);
                // 返回码需要为 206，而不是 200
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                // 设定文件读取开始位置（以字节为单位）
                targetFile.seek(range);
            }
            byte[] cache = new byte[1024 * 300];
            int flag;
            while ((flag = targetFile.read(cache)) != -1) {
                outputStream.write(cache, 0, flag);
            }

        } else {
            String message = "file: WW3.mp4 not exists";
            response.setHeader("Content-Type", "application/json");
            outputStream.write(message.getBytes(StandardCharsets.UTF_8));
        }
        outputStream.flush();
        outputStream.close();
    }
    
    // 下载文件
    @GetMapping("/download/{fileName}")
    public void downloadWithFileName(@PathVariable("fileName") String fileName,
                                     HttpServletResponse response) {
        File file = new File(fileService.getUrlByName(fileName));
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
