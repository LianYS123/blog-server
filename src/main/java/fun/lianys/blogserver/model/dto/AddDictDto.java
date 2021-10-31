package fun.lianys.blogserver.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AddDictDto {
  @NotNull
  private String key;
  private String value;
  private String description;
  private Integer createTime;
  private Integer updateTime;
}
