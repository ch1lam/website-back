package cn.chilam.websiteback.service.impl;

import cn.chilam.websiteback.mapper.CourseMapper;
import cn.chilam.websiteback.mapper.UserMapper;
import cn.chilam.websiteback.mapper.VideoMapper;
import cn.chilam.websiteback.pojo.Video;
import cn.chilam.websiteback.service.UploadService;
import cn.chilam.websiteback.util.FolderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
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
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CourseMapper courseMapper;

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
        String filePath = address + "/upload/video";
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

    /**
     * @description: 上传文件
     * @author: chilam
     * @param: file
     * @return: 上传是否成功
     * @date: 2020-04-10
     */
    @Override
    public boolean uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            return false;
        }
        String fileName = file.getOriginalFilename();
        String filePath = address + "/upload/video";
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
        String filePath =
                address + "/user/" + userMapper.selectByName(username).getId() + "_" + username +
                        "/avatar/";
        File dest = new File(filePath + fileName);
        try {
            // 创建文件夹
            FolderUtil.createFolder(filePath);
            userMapper.updateAvatarUrlByUsername(username, filePath + fileName);
            file.transferTo(dest);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * @description: 上传课程封面并更新封面url
     * @author: chilam
     * @param: file
     * @param: courseId
     * @param: sequence
     * @return: boolean
     * @date: 2020-05-2
     */
    @Override
    public boolean uploadPoster(MultipartFile file,Integer courseId,Integer sequence) {
        if (file.isEmpty()) {
            return false;
        }
        String courseName = courseMapper.selectByPrimaryKey(courseId).getName();

        String fileName = file.getOriginalFilename();
        String filePath = address + "/course/" + sequence + "_" + courseName + "/poster/";
        try {
            // 创建目录保存poster
            FolderUtil.createFolder(filePath);
            // 最终存放的路径
            File dest = new File(filePath + fileName);
            file.transferTo(dest);
            courseMapper.updatePictureUrlByIdAndUrl(courseId, filePath + fileName);
            return true;
        } catch (IOException e) {
            logger.error("错误异常" + e);
            return false;
        }

    }
}
