package cn.chilam.websiteback.controller;

import cn.chilam.websiteback.common.entity.ResultMap;
import cn.chilam.websiteback.pojo.Chapter;
import cn.chilam.websiteback.service.CourseService;
import cn.chilam.websiteback.service.FileService;
import cn.chilam.websiteback.service.UploadService;
import cn.chilam.websiteback.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: website-back
 * @description: 教师权限api
 * @author: chilam
 * @create: 2020-04-02 14:04
 **/
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;
    @Autowired
    UploadService uploadService;
    @Autowired
    FileService fileService;


    // 新增课程
    @PostMapping("/postCourse")
    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    public ResultMap postCourse(@RequestParam("courseName") String courseName,
                                @RequestParam("teacherName") String teacherName,
                                @RequestParam("sequence") Integer sequence) {
        if (courseService.postCourse(courseName, teacherName, sequence)) {
            return ResultMap.ok();
        } else {
            return ResultMap.error();
        }
    }

    // 修改课程信息
    @PostMapping("/putCourse")
    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    public ResultMap putCourse(@RequestParam("id") Integer id,
                               @RequestParam("courseName") String courseName,
                               @RequestParam("teacherName") String teacherName) {
        if (courseService.updateCourse(id, courseName, teacherName)) {
            return ResultMap.ok();
        } else {
            return ResultMap.error();
        }
    }

    // 上传课程封面
    @PostMapping("/postPoster")
    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    public ResultMap postPoster(MultipartFile file, @RequestParam("courseId") Integer courseId ,
                                @RequestParam("sequence") Integer sequence) {
        if (uploadService.uploadPoster(file, courseId,sequence)) {
            return ResultMap.ok();
        } else {
            return ResultMap.error();
        }
    }

    // 修改章节信息
    @PostMapping("/putChapter")
    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    public ResultMap putChapter(@RequestParam("id") Integer id,
                                @RequestParam("name") String name,
                                @RequestParam("sequence") Integer sequence) {
        if (courseService.updateChapter(id, name, sequence)) {
            return ResultMap.ok();
        } else {
            return ResultMap.error();
        }
    }

    // 获取course对应的chapterId
    @PostMapping("getBigChapterId")
    @RequiresRoles(value = {"teacher", "admin","student"}, logical = Logical.OR)
    public ResultMap getBigChapterId(@RequestParam("courseId") Integer courseId) {
        Map<String, Object> data = new HashMap<>();
        data.put("rootId", courseService.getChapterId(courseId));
        return ResultMap.ok().data(data);

    }


    // 新增一个章节
    @PostMapping("postChapter")
    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    public ResultMap postChapter(@RequestParam("chapterName") String chapterName,
                                 @RequestParam("parentId") Integer parentId,
                                 @RequestParam("sequence") Integer sequence) {
        Chapter chapter = new Chapter(chapterName, sequence);
        int a = courseService.postChapter(chapter, parentId);
        return ResultMap.ok().data("chapterId", a);
    }


    // 删除一个课程（根章节）
    @PostMapping("deleteCourse")
    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    public ResultMap deleteCourse(@RequestParam("id") Integer id ,
                                  @RequestParam("sequence") Integer sequence) {
        if (courseService.deleteCourseById(id,sequence)) {
            return ResultMap.ok();
        } else {
            return ResultMap.error();
        }
    }

    // 删除一个章节
    @PostMapping("deleteChapter")
    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    public ResultMap deleteChapter(@RequestParam("id") Integer id) {
        if (id == null) {
            return ResultMap.error().message("没有选择章节");
        }
        if (courseService.deleteChapter(id)) {
            return ResultMap.ok();
        } else {
            return ResultMap.error();
        }
    }

    // 上传视频
    @PostMapping("/uploadVideo")
    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    public ResultMap uploadVideo(@RequestParam("file") MultipartFile file, Integer id) {
        if (id == null) {
            return ResultMap.error().message("没有选择章节");
        }
        if (fileService.uploadVideo(file, id)) {
            return ResultMap.ok().message("上传成功");
        } else {
            return ResultMap.error().message("上传失败");
        }
    }

    // 上传文件
    @PostMapping("/uploadFile")
    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    public ResultMap uploadFile(@RequestParam("file") MultipartFile file, Integer id) {
        if (fileService.uploadFile(file, id)) {
            return ResultMap.ok().message("上传成功");
        } else {
            return ResultMap.error().message("上传失败");
        }
    }


    @PostMapping("/deleteFile")
    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    public ResultMap deleteFile(@RequestParam("id") Integer id) {
        if (fileService.deleteFile(id)) {
            return ResultMap.ok();
        } else {
            return ResultMap.error();
        }
    }

}
