package fun.lianys.blogserver.model.dto;


import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserDto {
  @NotNull(message = "用户名为必填项")
  private String username;
  @NotNull(message = "密码为必填项")
  private String password;
}
