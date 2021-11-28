package fun.lianys.blogserver.model.entity;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("essay")
public class Essay {
  private Integer id;
  private String html;
  private String raw;
  private User author;
  private Integer createTime;
  private Integer updateTime;
}
