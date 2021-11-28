package fun.lianys.blogserver.model.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EssayDto {
  private Integer id;
  @NotBlank
  private String html;
  @NotBlank
  private String raw;
  private Integer author;
  private Integer createTime;
  private Integer updateTime;
}
