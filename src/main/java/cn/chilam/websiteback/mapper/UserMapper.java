package cn.chilam.websiteback.mapper;

import cn.chilam.websiteback.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByName(String username);

    List<User> getAllUser();

    int updateByPrimaryKeySelective(User record);

    int updateAvatarUrlByUsername(@Param("username") String username,
                                  @Param("avatarUrl") String avatarUrl);

    int updateByPrimaryKey(User record);

    int updatePassword(@Param("username") String username, @Param("password") String password);

    String getPasswordByUsername(String username);

    String getRoleByUsername(String username);

    String getAvatarUrl(String username);

    int isExistsByUsername(String username);
}