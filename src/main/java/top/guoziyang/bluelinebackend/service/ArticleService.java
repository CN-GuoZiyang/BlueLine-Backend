package top.guoziyang.bluelinebackend.service;

import com.github.pagehelper.Page;
import top.guoziyang.bluelinebackend.entity.ArticlePo;
import top.guoziyang.bluelinebackend.entity.ArticleSummaryPo;
import top.guoziyang.bluelinebackend.model.CommentDto;
import top.guoziyang.bluelinebackend.entity.PreviousAndNextArticlePo;

import java.util.List;

public interface ArticleService {

    ArticlePo getArticleDto(int articleId);

    List<CommentDto> getCommentTree(int articleId);

    PreviousAndNextArticlePo getPreviousArticle(int articleId);

    PreviousAndNextArticlePo getNextArticle(int articleId);

    Page<ArticleSummaryPo> getArticleList(int page, int count);

}
