package cn.chilam.websiteback.service.impl;

import cn.chilam.websiteback.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @program: website-back
 * @description: 上传服务实现类
 * @author: chilam
 * @create: 2020-04-09 20:08
 **/
@Service
public class UploadServiceImpl implements UploadService {

    /**
     * @description: 上传视频
     * @author: chilam
     * @param: file
     * @return: 上传是否成功
     * @date: 2020-04-09
     */
    @Override
    public boolean uploadVideo(MultipartFile file) {
        if (file.isEmpty()) {
            return false;
        }
        String fileName = file.getOriginalFilename();
        String filePath = "D://Videos/Upload/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
