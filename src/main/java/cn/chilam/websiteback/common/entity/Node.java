package cn.chilam.websiteback.common.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: website-back
 * @description: 闭包表数据结点
 * @author: chilam
 * @create: 2020-05-9 23:36
 **/
public class Node {
    private Integer id; // chapter中的id存放在这
    private String label; // 结点名字
    private Integer sequence; // 在当前层级的index
    private Integer VideoId; // 当前节点的videoId
    private List<Node> children; // 存放子结点

    public Node() {
        this.children = new ArrayList<Node>();
    }

    public Node(List<Node> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getVideoId() {
        return VideoId;
    }

    public void setVideoId(Integer videoId) {
        VideoId = videoId;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
}
