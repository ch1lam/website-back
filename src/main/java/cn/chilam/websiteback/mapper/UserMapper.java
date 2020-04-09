package cn.chilam.websiteback.mapper;

import cn.chilam.websiteback.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    String getPasswordByUsername(String username);
    String getRoleByUsername(String username);

    List<User> getAllUser();

    int isExistsByUsername(String username);
}