package cn.chilam.websiteback.service.impl;

import cn.chilam.websiteback.mapper.UserMapper;
import cn.chilam.websiteback.mapper.VideoMapper;
import cn.chilam.websiteback.pojo.Video;
import cn.chilam.websiteback.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @program: website-back
 * @description: 上传服务实现类
 * @author: chilam
 * @create: 2020-04-09 20:08
 **/
@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private UserMapper userMapper;

    @Value("${upload.file.location}")
    private String address;

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
        String filePath = address+"/upload/video";
        File dest = new File(filePath + fileName);
        long fileSize = file.getSize();
        try {
            Video video = new Video(fileName,
                    filePath + fileName, fileSize);
            videoMapper.insert(video);
            file.transferTo(dest);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean uploadAvatar(MultipartFile file, String username) {
        if (file.isEmpty()) {
            return false;
        }
        String fileName = file.getOriginalFilename();
        String filePath = address+"/upload/avatar";
        File dest = new File(filePath + fileName);
        try {
            userMapper.updateAvatarUrlByUsername(username, filePath + fileName);
            file.transferTo(dest);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
