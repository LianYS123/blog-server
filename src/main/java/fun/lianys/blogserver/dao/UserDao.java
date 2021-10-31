package fun.lianys.blogserver.dao;

import fun.lianys.blogserver.model.entity.JwtUser;
import fun.lianys.blogserver.model.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    @Select("select * from user where username=#{username}")
    public JwtUser getUserByName(String username);

    @Select("select * from user where id=#{id}")
    public User getUserById(Integer id);
}
