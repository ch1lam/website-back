package cn.chilam.websiteback.mapper;

import cn.chilam.websiteback.pojo.Chapter;
import cn.chilam.websiteback.pojo.ChapterClosure;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: website-back
 * @description:
 * @author: chilam
 * @create: 2020-04-07 15:56
 **/
@Repository
public interface ChapterMapper {
    int insert(Chapter record);

    boolean updateByPrimaryKeySelective(Chapter record);

    // 获取某结点到根结点的路径，返回层级升序排列的list
    List<ChapterClosure> selectRootPathById(Integer id);

    Chapter selectById(Integer id);

    // 查询某一层的节点的数量。
    int selectCountByLayer(int distance);

    // 连表查询某个节点的子树中所有的节点，不包括参数所指定的节点
    List<Chapter> selectDescendant(int id);

    // 查询某个节点的第 n 级子节点
    List<Chapter> selectSubLayer(int ancestor, int n);

    // 复制父节点的路径结构，并修改 descendant 和 distance
    void insertPath(int id, int parent);

    // 在关系表中插入对自身的连接
    void insertNode(int id);

    // 查询某个节点的子树中所有的节点的id，不包括参数所指定的节点
    List<ChapterClosure> selectDescendantId(int id);

    Boolean isExistsById(int id);

    // 获取某结点深度
    int selectMaxDistanceById(int id);

    // 查询某结点的所有后代（包括自己）
    List<Chapter> selectAllDescendantById(Integer id);

    // 查询某结点下一层的所有结点
    List<ChapterClosure> selectNextDescendantById(Integer id);

    int deleteById(int id);

    void deletePath(int id);

    


}
