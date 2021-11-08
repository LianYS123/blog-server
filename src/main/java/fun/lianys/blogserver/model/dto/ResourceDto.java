package fun.lianys.blogserver.model.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDto {
  private int id;
  @NotNull
  private String resourceName;
  @NotNull
  private String src;
  private String type;
  private Integer size;
  private String description;
  private Integer createTime;
  private Integer updateTime;
}
