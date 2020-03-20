package cn.chilam.websiteback.mapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void getUserInfoAll() {
        System.out.println("输出："+userMapper.getUserInfoAll());
    }

    @Test
    void getUserInfoByUsername() {


    }
}