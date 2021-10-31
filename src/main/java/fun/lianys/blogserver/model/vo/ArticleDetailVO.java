package fun.lianys.blogserver.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVO {
  
    private Integer id;
    private String articleName;
    private String html;
    private String raw;
    private String cover; // 封面图片
    private Integer authorId; // 用户id
    private String authorName; 
    private Integer createTime;
    private Integer updateTime;
}
