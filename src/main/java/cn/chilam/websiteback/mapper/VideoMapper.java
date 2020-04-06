package cn.chilam.websiteback.mapper;

import cn.chilam.websiteback.pojo.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: website-back
 * @description:
 * @author: chilam
 * @create: 2020-04-05 21:29
 **/
@Repository
public interface VideoMapper {
    List<Video> getVideoInfoByName(String name);

    int insert(Video record);
}
