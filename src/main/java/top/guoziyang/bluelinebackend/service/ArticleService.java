package top.guoziyang.bluelinebackend.service;

import top.guoziyang.bluelinebackend.model.ArticleDto;
import top.guoziyang.bluelinebackend.model.CommentDto;
import top.guoziyang.bluelinebackend.model.PreviousAndNextArticleDto;

import java.util.List;

public interface ArticleService {

    ArticleDto getArticleDto(int articleId);

    List<CommentDto> getCommentTree(int articleId);

    PreviousAndNextArticleDto getPreviousArticle(int articleId);

    PreviousAndNextArticleDto getNextArticle(int articleId);

}
