package fun.lianys.blogserver.dao;

import fun.lianys.blogserver.model.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    @Select("select * from user where username=#{username}")
    public User getUserByName(String username);
}
