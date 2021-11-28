package fun.lianys.blogserver.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenUser {
  private String username;
  private Integer id;
}
