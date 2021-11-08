package fun.lianys.blogserver.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EditArticleDto {
    private Integer id;
    @NotNull
    private String articleName;
    private String cover; // 封面
    @NotNull
    private String html;
    @NotNull
    private String raw;
    private Integer updateTime;
    private Integer[] tags;
}
