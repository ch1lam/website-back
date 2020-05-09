package cn.chilam.websiteback.service.impl;

import cn.chilam.websiteback.mapper.CourseMapper;
import cn.chilam.websiteback.pojo.Course;
import cn.chilam.websiteback.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: website-back
 * @description:
 * @author: chilam
 * @create: 2020-04-15 08:50
 **/
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getAllCourseInfo() {
        return courseMapper.selectAllCourse();
    }

    @Override
    public String getPosterUrl(Integer id) {
        return courseMapper.selectPosterUrlById(id);
    }
}
