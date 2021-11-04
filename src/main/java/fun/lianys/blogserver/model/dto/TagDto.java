package fun.lianys.blogserver.model.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {
  private int id;
  @NotNull
  private String tagName;
  private String color;
  private String description;
  private Integer createTime;
  private Integer updateTime;
}
