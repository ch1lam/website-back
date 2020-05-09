package cn.chilam.websiteback.service;

import cn.chilam.websiteback.mapper.CourseMapper;
import cn.chilam.websiteback.pojo.Course;

import java.util.List;

/**
 * @program: website-back
 * @description: 课程服务
 * @author: chilam
 * @create: 2020-04-15 08:49
 **/
public interface CourseService {

    List<Course> getAllCourseInfo();

    String getPosterUrl(Integer id);

}
