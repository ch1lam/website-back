package cn.chilam.websiteback.mapper;

import cn.chilam.websiteback.pojo.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    List<Course> selectAllCourse();

    boolean updateByPrimaryKeySelective(Course record);

    String selectPosterUrlById(Integer id);

    // 获取最后一个自增id，可用于高并发
    int selectLastId();

    int updatePictureUrlByIdAndUrl(Integer id,String pictureUrl);
}