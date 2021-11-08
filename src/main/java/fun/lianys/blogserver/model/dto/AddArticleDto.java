package fun.lianys.blogserver.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AddArticleDto {
    private Integer id;
    private Integer author; // 作者
    @NotNull
    private String articleName;
    private String cover; // 封面
    @NotNull
    private String html;
    @NotNull
    private String raw;
    private Integer createTime;
    private Integer updateTime;
    private Integer[] tags;
}
