package top.guoziyang.bluelinebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import top.guoziyang.bluelinebackend.entity.Article;
import top.guoziyang.bluelinebackend.model.PreviousAndNextArticleDto;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Article getArticleById(Integer id);

    @Query(value = "SELECT id,title FROM article WHERE id<?1 ORDER BY id DESC LIMIT 0,1;", nativeQuery = true)
    PreviousAndNextArticleDto getPreviousArticle(Integer id);

    @Query(value = "SELECT id,title FROM article WHERE id>?1 LIMIT 0,1;", nativeQuery = true)
    PreviousAndNextArticleDto getNextArticle(Integer id);

}
