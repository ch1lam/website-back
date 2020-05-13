package cn.chilam.websiteback.pojo;

/**
 * @program: website-back
 * @description: 章节课程关系
 * @author: chilam
 * @create: 2020-04-07 15:52
 **/
public class Chapter {
    private Integer id;
    private String chapterName;
    private Integer parentId; // 父节点id
    private Integer videoId; // 视频id 默认为0则代表非叶子结点
    private Integer sequence; // 排序


    public Chapter(Integer id, String chapterName, Integer parentId, Integer videoId,
                   Integer sequence) {
        this.id = id;
        this.chapterName = chapterName;
        this.parentId = parentId;
        this.videoId = videoId;
        this.sequence = sequence;
    }

    public Chapter(Integer id, String chapterName, Integer sequence) {
        this.id = id;
        this.chapterName = chapterName;
        this.sequence = sequence;
    }

    public Chapter(String chapterName, Integer sequence) {
        this.chapterName = chapterName;
        this.sequence = sequence;
    }

    public Chapter(String chapterName, Integer parentId, Integer videoId,
                   Integer sequence) {
        this.chapterName = chapterName;
        this.parentId = parentId;
        this.videoId = videoId;
        this.sequence = sequence;
    }


    public Chapter(String chapterName, Integer parentId, Integer sequence) {
        this.chapterName = chapterName;
        this.parentId = parentId;
        this.sequence = sequence;
    }

    public Chapter(Integer id, String chapterName) {
        this.id = id;
        this.chapterName = chapterName;
    }


    public Chapter() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
