package fun.lianys.blogserver.model.entity;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("user")
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer createTime;
    private Integer updateTime;
}
