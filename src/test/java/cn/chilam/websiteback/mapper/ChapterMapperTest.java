package cn.chilam.websiteback.mapper;

import cn.chilam.websiteback.pojo.Chapter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
class ChapterMapperTest {
    @Autowired
    private ChapterMapper chapterMapper;

    @Test
    void insert() {
        Chapter test=new Chapter("test2",2);
        System.out.println(chapterMapper.insert(test));
        System.out.println(test.getId());
    }
}