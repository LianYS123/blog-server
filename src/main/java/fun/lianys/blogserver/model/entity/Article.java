package fun.lianys.blogserver.model.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

// 数据库使用的实体类
@Data
@Alias("article")
public class Article {
    private Integer id;
    private String articleName;
    private String html;
    private String raw;
    private String cover;
    private User author;
    private Integer createTime;
    private Integer updateTime;
}
