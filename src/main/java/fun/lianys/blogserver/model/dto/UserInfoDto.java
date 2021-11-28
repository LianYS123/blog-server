package fun.lianys.blogserver.model.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserInfoDto {
    private Integer id;
    @NotBlank
    private String username;
    private String password;
    private String avatar;
    private Integer createTime;
    private Integer updateTime;
}
