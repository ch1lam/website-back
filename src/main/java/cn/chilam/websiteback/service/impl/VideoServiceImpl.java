package cn.chilam.websiteback.service.impl;

import cn.chilam.websiteback.mapper.ChapterMapper;
import cn.chilam.websiteback.mapper.VideoMapper;
import cn.chilam.websiteback.pojo.Chapter;
import cn.chilam.websiteback.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @program: website-back
 * @description: 视频实现类
 * @author: chilam
 * @create: 2020-04-08 16:29
 **/
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoMapper videoMapper;

    @Autowired
    ChapterMapper chapterMapper;


    @Override
    public String getUrlById(Integer id) {

        Chapter chapter = chapterMapper.selectById(id);
        return videoMapper.selectUrlById(chapter.getVideoId());
    }

}
