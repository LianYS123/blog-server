package fun.lianys.blogserver.model.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ChangePasswordDto {
  @NotBlank
  String oldPassword;
  @NotBlank
  String newPassword;
  
}
