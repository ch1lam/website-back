package cn.chilam.websiteback.mapper;

import cn.chilam.websiteback.pojo.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: website-back
 * @description: 视频mapper接口
 * @author: chilam
 * @create: 2020-04-05 21:29
 **/
@Repository
public interface VideoMapper {
    int insert(Video record);

    List<Video> getVideoInfoByName(String name);

    String getUrlByName(String name);

    String selectUrlById(Integer id);

}
