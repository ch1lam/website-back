package cn.chilam.websiteback.service;

import cn.chilam.websiteback.common.entity.Node;
import cn.chilam.websiteback.pojo.Chapter;
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

    Boolean postCourse(String courseName, String teacherName);

    Boolean updateCourse(Integer id, String courseName, String teacherName);

    Boolean updateChapter(Integer id, String name,Integer sequence);

    int postChapter(Chapter chapter, Integer parentId);

    List<Node> getAllChapterById(Integer id);



    boolean deleteCourseById(Integer id);

    boolean deleteChapter(Integer id);


}
