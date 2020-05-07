package cn.chilam.websiteback.pojo;

/**
 * @program: website-back
 * @description: 章节课程关系
 * @author: chilam
 * @create: 2020-04-07 15:52
 **/
public class CourseDetail {
    private Integer id;
    private String className;
    private Integer videoId;
    private Integer classKey; // 章节排序
    private Integer courseId; // 课程id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getClassKey() {
        return classKey;
    }

    public void setClassKey(Integer classKey) {
        this.classKey = classKey;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
