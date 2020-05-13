package cn.chilam.websiteback.pojo;

import java.io.Serializable;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Integer firstClassId; // 课程第一个小节的id
    private String teacherName;
    private String pictureUrl;

    public Course(Integer id, String name, Integer firstClassId, String teacherName,
                  String pictureUrl) {
        this.id = id;
        this.name = name;
        this.firstClassId = firstClassId;
        this.teacherName = teacherName;
        this.pictureUrl = pictureUrl;
    }

    public Course(String courseName, String teacherName) {
        this.name = courseName;
        this.teacherName = teacherName;
    }

    public Course(String name, Integer firstClassId, String teacherName) {
        this.name = name;
        this.firstClassId = firstClassId;
        this.teacherName = teacherName;
    }

    public Course(Integer id, String name, String teacherName) {
        this.id = id;
        this.name = name;
        this.teacherName = teacherName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getfirstClassId() {
        return firstClassId;
    }

    public void setfirstClassId(Integer firstClassId) {
        this.firstClassId = firstClassId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }


}