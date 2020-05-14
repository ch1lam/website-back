package cn.chilam.websiteback.service;

import cn.chilam.websiteback.pojo.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program: website-back
 * @description: 文件服务
 * @author: chilam
 * @create: 2020-03-20
 **/
public interface FileService {

    boolean uploadVideo(MultipartFile file, Integer id);

    boolean uploadFile(MultipartFile file, Integer id);

    void DownloadFile();

    List<File> searchAllFile();

    void deleteFile(Integer id);

    File getFileInfo(Integer id);


}
