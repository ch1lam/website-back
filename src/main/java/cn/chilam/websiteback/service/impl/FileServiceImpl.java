package cn.chilam.websiteback.service.impl;

import cn.chilam.websiteback.mapper.ChapterMapper;
import cn.chilam.websiteback.mapper.FileMapper;
import cn.chilam.websiteback.mapper.VideoMapper;
import cn.chilam.websiteback.pojo.Chapter;
import cn.chilam.websiteback.pojo.ChapterClosure;
import cn.chilam.websiteback.pojo.File;
import cn.chilam.websiteback.pojo.Video;
import cn.chilam.websiteback.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @program: website-back
 * @description: 文件服务实现类
 * @author: chilam
 * @create: 2020-04-21 18:06
 **/
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    VideoMapper videoMapper;
    @Autowired
    FileMapper fileMapper;
    @Autowired
    ChapterMapper chapterMapper;

    // 上传文件夹路径
    @Value("${upload.file.location}")
    private String address;


    /**
     * @description: 上传文件
     * @author: chilam
     * @param: file
     * @return: boolean
     * @date: 2020-04-01
     */
    @Override
    @Transactional
    public boolean uploadVideo(MultipartFile file, Integer id) {
        if (file.isEmpty()) {
            return false;
        }
        String fileName = file.getOriginalFilename();
        // 生成目录url
        StringBuilder url = new StringBuilder(address + "/course/");

        for (ChapterClosure chap : chapterMapper.selectRootPathById(id)) {
            Chapter tmp = chapterMapper.selectById(chap.getAncestor());
            url.append(tmp.getSequence()).append("_").append(tmp.getChapterName()).append("/");
        }
        String filePath = url.toString() + fileName;
        java.io.File dest = new java.io.File(filePath);
        long fileSize = file.getSize();
        try {
            Video video = new Video(fileName,
                    filePath, fileSize);
            videoMapper.insert(video);
            // 取刚才插入的视频id
            int VideoId = video.getId();

            // 更新视频id
            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setVideoId(VideoId);
            chapterMapper.updateByPrimaryKeySelective(chapter);
            file.transferTo(dest);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean uploadFile(MultipartFile file, Integer id) {
        if (file.isEmpty()) {
            return false;
        }
        String fileName = file.getOriginalFilename();
        // 生成目录url
        StringBuilder url = new StringBuilder(address + "/course/");

        for (ChapterClosure chap : chapterMapper.selectRootPathById(id)) {
            Chapter tmp = chapterMapper.selectById(chap.getAncestor());
            url.append(tmp.getSequence()).append("_").append(tmp.getChapterName()).append("/");
        }
        String filePath = url.toString() + fileName;
        java.io.File dest = new java.io.File(filePath);
        long fileSize = file.getSize();
        try {
            File newFile = new File(fileName, filePath, fileSize, id);
            fileMapper.insert(newFile);

            // 存文件
            file.transferTo(dest);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void DownloadFile() {
    }

    @Override
    public List<File> searchAllFile() {
        return fileMapper.getAllFileInfo();
    }

    @Override
    public void deleteFile(Integer id) {
        fileMapper.deleteById(id);
    }

    @Override
    public File getFileInfo(Integer id) {
        return fileMapper.getFileInfoById(id);
    }


}
