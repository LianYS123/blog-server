package fun.lianys.blogserver.dao;

import fun.lianys.blogserver.model.dto.UserInfoDto;
import fun.lianys.blogserver.model.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    @Select("select * from user where username=#{username}")
    public User getUserByName(String username);

    @Select("select * from user where id=#{id}")
    public User getUserById(Integer id);

    @Update("update user set password=#{newPassword} where id=#{id}")
    public Integer updatePassword(Integer id, String newPassword);

    @Update("update user set username=#{username}, avatar=#{avatar}, update_time=#{updateTime} where id=#{id}")
    public void updateUserInfo(UserInfoDto dto);
}
