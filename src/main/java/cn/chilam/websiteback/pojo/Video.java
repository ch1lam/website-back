package cn.chilam.websiteback.pojo;

import java.io.Serializable;

/**
 * @program: website-back
 * @description: 视频信息
 * @author: chilam
 * @create: 2020-04-05 20:55
 **/
public class Video implements Serializable {

    private Integer id;
    private String name;
    private String url;
    private Integer size;

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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
