package cn.chilam.websiteback.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: website-back
 * @description: 文件类
 * @author: chilam
 * @create: 2020-03-28 12:49
 **/
public class File implements Serializable {
    private Integer id;
    private String name;
    private String url;
    private Long size;
    private Date date; // 上传时间
    private Integer chapterId; // 对应章节的id

    public File(String name, String url, Long size) {
        this.name = name;
        this.url = url;
        this.size = size;
    }

    public File(String name, String url, Long size, Integer chapterId) {
        this.name = name;
        this.url = url;
        this.size = size;
        this.chapterId = chapterId;
    }

    public File() {
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
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }
}
