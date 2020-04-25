package top.guoziyang.bluelinebackend.model;

import lombok.Builder;

import java.util.List;

@Builder
public class ArticlePageVo {

    private ArticleDto article;
    private List<CommentDto> comments;

    private PreviousAndNextArticleDto previousArticle;
    private PreviousAndNextArticleDto nextArticle;

}
