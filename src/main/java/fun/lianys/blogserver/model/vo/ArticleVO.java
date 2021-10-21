package fun.lianys.blogserver.model.vo;

import lombok.Data;

// 返回的数据
@Data
public class ArticleVO {
    private Integer id;
    private String articleName;
    private String html;
    private String raw;
}
