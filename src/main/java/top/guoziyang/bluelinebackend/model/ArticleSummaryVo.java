package top.guoziyang.bluelinebackend.model;

import lombok.Builder;
import lombok.Data;
import top.guoziyang.bluelinebackend.entity.ArticleSummaryPo;

import java.util.List;

@Builder
@Data
public class ArticleSummaryVo {

    private List<ArticleSummaryPo> articles;
    private Integer currentPage;
    private Integer totalPage;
    private Integer pageSize;

}
