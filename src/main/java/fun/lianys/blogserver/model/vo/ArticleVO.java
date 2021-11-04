package fun.lianys.blogserver.model.vo;

import java.util.List;

import fun.lianys.blogserver.model.dto.TagDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 返回的数据
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO {
    private Integer id;
    private String articleName;
    private String summary;
    private String cover; // 封面图片
    private Integer authorId; // 用户id
    private String authorName; // 用户id
    private Integer createTime;
    private Integer updateTime;
    private List<TagDto> tags;
}
