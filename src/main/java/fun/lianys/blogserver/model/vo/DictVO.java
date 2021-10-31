package fun.lianys.blogserver.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictVO {
  private int id;
  private String key;
  private String value;
  private String description;
  private Integer createTime;
  private Integer updateTime;
}
