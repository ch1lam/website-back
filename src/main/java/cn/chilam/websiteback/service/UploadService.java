package cn.chilam.websiteback.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @program: website-back
 * @description: 上传文件服务接口
 * @author: chilam
 * @create: 2020-04-09 20:07
 **/
public interface UploadService {
    boolean uploadVideo(MultipartFile file);

    boolean uploadAvatar(MultipartFile file,String username);


    boolean uploadPoster(MultipartFile file,Integer courseId);



}
