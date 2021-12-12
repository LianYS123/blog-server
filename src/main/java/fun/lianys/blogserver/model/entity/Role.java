package fun.lianys.blogserver.model.entity;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("role")
public class Role {
  private Integer id;
  private String roleCode;
  private String roleName;
  private String roleDesc;
  private Integer createTime;
  private Integer updateTime;
}
