package fun.lianys.blogserver.model.dto;

import lombok.Data;

@Data
public class PageParamDto {
    private Integer page = 1;
    private Integer pageSize = 20;
    private String keyword;
    private String order = "update_time";
    private String orderType = "desc";
}
