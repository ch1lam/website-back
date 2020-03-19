package cn.chilam.websiteback.mapper;

import cn.chilam.websiteback.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: website-back
 * @description: 用户接口
 * @author: chilam
 * @create: 2020-03-04 17:02
 **/
@Repository
public interface UserMapper {
    List<User> getUserInfoAll();

    User getUserInfoByUsername(String username);
}
