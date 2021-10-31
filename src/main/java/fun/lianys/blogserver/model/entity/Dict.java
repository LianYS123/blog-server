package fun.lianys.blogserver.model.entity;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("dict")
public class Dict {
  private int id;
  private String key;
  private String value;
  private String description;
  private Integer createTime;
  private Integer updateTime;
}
