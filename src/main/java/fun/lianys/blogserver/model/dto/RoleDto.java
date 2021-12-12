package fun.lianys.blogserver.model.dto;

import lombok.Data;

@Data
public class RoleDto {
  private Integer id;
  private String roleCode;
  private String roleName;
  private String roleDesc;
  private Integer createTime;
  private Integer updateTime;
}
