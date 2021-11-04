package fun.lianys.blogserver.model.entity;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("tag")
public class Tag {
  private int id;
  private String tagName;
  private String color;
  private String description;
  private Integer createTime;
  private Integer updateTime;
}
