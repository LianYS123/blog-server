package fun.lianys.blogserver.model.dto;

import lombok.Data;

// 接收从前端传过来的请求参数
@Data
public class ArticleQueryDto {
    private Integer id;
    private Integer page = 1;
    private Integer pageSize = 20;
    private String order;
    private String orderType = "asc";
    private String keyword;
}
