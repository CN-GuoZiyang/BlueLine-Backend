package top.guoziyang.bluelinebackend.model;

import lombok.Builder;
import lombok.Data;
import top.guoziyang.bluelinebackend.entity.ArticlePo;
import top.guoziyang.bluelinebackend.entity.PreviousAndNextArticlePo;

import java.util.List;

@Builder
@Data
public class ArticlePageVo {

    private ArticlePo article;
    private List<CommentDto> comments;

    private PreviousAndNextArticlePo previousArticle;
    private PreviousAndNextArticlePo nextArticle;

}
