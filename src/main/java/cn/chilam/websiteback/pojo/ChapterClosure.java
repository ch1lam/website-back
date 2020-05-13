package cn.chilam.websiteback.pojo;

/**
 * @program: website-back
 * @description: 闭包表 记录Chapter的距离
 * @author: chilam
 * @create: 2020-05-9 19:46
 **/
public class ChapterClosure {
    private Integer ancestor; // 祖先：上级节点的 id
    private Integer descendant; // 子代：下级节点的 id
    private Integer distance; // 距离：子代到祖先中间隔了多少级

    public Integer getAncestor() {
        return ancestor;
    }

    public void setAncestor(Integer ancestor) {
        this.ancestor = ancestor;
    }

    public Integer getDescendant() {
        return descendant;
    }

    public void setDescendant(Integer descendant) {
        this.descendant = descendant;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
