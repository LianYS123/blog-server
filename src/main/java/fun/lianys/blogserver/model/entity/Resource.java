package fun.lianys.blogserver.model.entity;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("resource")
public class Resource {
  private int id;
  private String resourceName;
  private String src;
  private String type;
  private Integer size;
  private String description;
  private Integer createTime;
  private Integer updateTime;
}
