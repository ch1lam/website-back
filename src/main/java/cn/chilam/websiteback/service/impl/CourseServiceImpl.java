package cn.chilam.websiteback.service.impl;

import cn.chilam.websiteback.common.entity.Node;
import cn.chilam.websiteback.mapper.ChapterMapper;
import cn.chilam.websiteback.mapper.CourseMapper;
import cn.chilam.websiteback.pojo.Chapter;
import cn.chilam.websiteback.pojo.ChapterClosure;
import cn.chilam.websiteback.pojo.Course;
import cn.chilam.websiteback.service.CourseService;
import cn.chilam.websiteback.util.FolderUtil;
import cn.chilam.websiteback.util.SQLUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @program: website-back
 * @description:
 * @author: chilam
 * @create: 2020-04-15 08:50
 **/
@Service
public class CourseServiceImpl implements CourseService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 项目文件夹路径
    @Value("${upload.file.location}")
    private String address;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    public List<Course> getAllCourseInfo() {
        return courseMapper.selectAllCourse();
    }

    @Override
    public String getPosterUrl(Integer id) {
        return courseMapper.selectPosterUrlById(id);
    }

    /**
     * @description: 先插入course的信息，再获取course的id，作为新建文件夹命名依据
     * @author: chilam
     * @param: courseName
     * @param: teacherName
     * @return: java.lang.Boolean
     * @date: 2020-05-2
     */
    @Override
    public Boolean postCourse(String courseName, String teacherName,Integer sequence) {

        int id;
        try {
            // 新建一个根章节，课程即根章节，parentId默认为0
            Chapter chapter = new Chapter(courseName, 0, sequence);
            chapterMapper.insert(chapter);
            // chapter表中的id，准备插入course表中的first_class_id
            Integer chapterId = chapter.getId();
            chapterMapper.insertPath(chapter.getId(), 0);
            chapterMapper.insertNode(chapter.getId());
            // 创建course对象
            Course course = new Course(courseName, chapterId, teacherName);

            // 插入course表并获取id
            courseMapper.insertSelective(course);
            String url = address + "/course/" + sequence.toString()+ "_" + courseName;
            // 创建文件夹
            FolderUtil.createFolder(url);

            return true;
        } catch (Exception e) {
            logger.error("创建课程失败，课程名：" + courseName + " 教授老师：" + teacherName + "错误异常：" + e);
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean updateCourse(Integer id, String courseName, String teacherName) {
        try {
            Course course = new Course(id, courseName, teacherName);
            Chapter chapter = new Chapter(id, courseName);
            courseMapper.updateByPrimaryKeySelective(course);
            chapterMapper.updateByPrimaryKeySelective(chapter);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    @Override
    @Transactional
    public Boolean updateChapter(Integer id, String name, Integer sequence) {
        try {
            Chapter chapter = new Chapter(id, name, sequence);
            chapterMapper.updateByPrimaryKeySelective(chapter);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * @description: 新增一个章节，其 ID 属性将自动生成或计算，并返回
     * 新增章节的继承关系由 parent 属性指定，parent 为 0 表示该为一级章节即课程（Course）。
     * @author: chilam
     * @param: chapter 章节实体对象
     * @param: parent 上级章节id
     * @return: int
     * @date: 2020-05-10
     */
    @Transactional
    @Override
    public int postChapter(Chapter chapter, Integer parentId) {
        if (parentId < 0) throw new IllegalArgumentException("参数" + "parent" + "不能为负:" + parentId);
        if (parentId > 0 && chapterMapper.isExistsById(parentId) == null) {
            throw new IllegalArgumentException("指定的上级分类不存在");
        }
        try {
            chapter.setParentId(parentId);
            chapterMapper.insert(chapter);
            chapterMapper.insertPath(chapter.getId(), parentId);
            chapterMapper.insertNode(chapter.getId());

            // 生成目录url
            StringBuilder url = new StringBuilder(address + "/course/");
            for (ChapterClosure chap : chapterMapper.selectRootPathById(chapter.getId())) {
                Chapter tmp = chapterMapper.selectById(chap.getAncestor());
                url.append(tmp.getSequence()).append("_").append(tmp.getChapterName()).append("/");
            }
            // 创建文件夹
            FolderUtil.createFolder(url.toString());
            return chapter.getId();
        } catch (PersistenceException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public List<Node> getAllChapterById(Integer id) {
        // 最终返回List
        // 中间使用key-value查找效率高
        Map<Integer, Node> tmp = new HashMap<>();
        // foreach所有子结点，存在tmp中
        for (Chapter chapter : chapterMapper.selectAllDescendantById(id)) {
            Node data = new Node();
            data.setId(chapter.getId());
            data.setLabel(chapter.getChapterName());
            data.setSequence(chapter.getSequence());
            data.setVideoId(chapter.getVideoId());
            tmp.put(chapter.getId(), data);
        }

        // 遍历tmp
        for (Integer key : tmp.keySet()) {
            if (CollectionUtils.isNotEmpty(chapterMapper.selectNextDescendantById(key))) {
                // 遍历当前 id=key的节点的儿子结点
                for (ChapterClosure path : chapterMapper.selectNextDescendantById(key)) {
                    // 获取后代id
                    Integer childId = path.getDescendant();
                    // 把children取出来，father是当前节点的children，也是准备拼接的子结点的父节点
                    List<Node> father = tmp.get(key).getChildren();
                    // 组装父子结构
                    father.add(tmp.get(childId));
                }
            }
        }
        // 以List<Node>返回方便前端使用，只需返回课程的children
        return new ArrayList<>(tmp.get(id).getChildren());
    }

    @Override
    public int getChapterId(Integer id) {
        return courseMapper.selectByPrimaryKey(id).getfirstClassId();
    }

    @Transactional
    @Override
    public boolean deleteCourseById(Integer id,Integer sequence) {
        try {
            // firstClassId是根据course.id得到的课程 在chapter表中对应的chapter.id
            int chapterId = courseMapper.selectByPrimaryKey(id).getfirstClassId();

            // 删除当前章节节点
            SQLUtil.checkEffective(chapterMapper.deleteById(chapterId));
            // 删除当前章节为终点的路径
            chapterMapper.deletePath(chapterId);

            // 遍历当前节点的所有子孙结点 不包括自己
            for (ChapterClosure des : chapterMapper.selectDescendantId(chapterId)) {
                SQLUtil.checkEffective(chapterMapper.deleteById(des.getDescendant()));
                chapterMapper.deletePath(des.getDescendant());
            }

            // 要删除的course对象
            Course course = courseMapper.selectByPrimaryKey(id);
            // 课程文件夹
            String url = address + "/course/" + sequence + "_" + course.getName();
            // 删除课程文件夹及其内容
            FolderUtil.deleteFolder(url);
            // 在course表中删除课程
            courseMapper.deleteByPrimaryKey(id);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteChapter(Integer id) {
        try {
            // 生成目录url
            StringBuilder url = new StringBuilder(address + "/course/");
            for (ChapterClosure chap : chapterMapper.selectRootPathById(id)) {
                Chapter tmp = chapterMapper.selectById(chap.getAncestor());
                url.append(tmp.getSequence()).append("_").append(tmp.getChapterName()).append("/");
            }
            logger.info("删除章节："+url);
            // 删除文件夹及其内容
            FolderUtil.deleteFolder(url.toString());
            // 删除当前章节节点
            SQLUtil.checkEffective(chapterMapper.deleteById(id));
            // 删除当前章节为终点的路径
            chapterMapper.deletePath(id);
            // 遍历当前节点的所有子孙结点 不包括自己
            for (ChapterClosure des : chapterMapper.selectDescendantId(id)) {
                SQLUtil.checkEffective(chapterMapper.deleteById(des.getDescendant()));
                chapterMapper.deletePath(des.getDescendant());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}