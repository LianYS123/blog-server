package fun.lianys.blogserver.model.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
}
