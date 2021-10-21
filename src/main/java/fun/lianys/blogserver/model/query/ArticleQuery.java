package fun.lianys.blogserver.model.query;

import lombok.Data;

// 接收从前端传过来的请求参数
@Data
public class ArticleQuery {
    private Integer id;
    private String articleName;
    private String html;
    private String raw;
}
