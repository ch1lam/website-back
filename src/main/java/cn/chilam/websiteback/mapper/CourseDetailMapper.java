package cn.chilam.websiteback.mapper;

import cn.chilam.websiteback.pojo.CourseDetail;
import org.springframework.stereotype.Repository;

/**
 * @program: website-back
 * @description:
 * @author: chilam
 * @create: 2020-04-07 15:56
 **/
@Repository
public interface CourseDetailMapper {
    int insert(CourseDetail record);

    int deleteByPrimaryKey(Integer id);
}
