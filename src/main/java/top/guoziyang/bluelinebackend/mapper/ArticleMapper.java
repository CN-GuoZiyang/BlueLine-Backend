package top.guoziyang.bluelinebackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.guoziyang.bluelinebackend.entity.ArticleSummaryPo;
import top.guoziyang.bluelinebackend.entity.PreviousAndNextArticlePo;
import top.guoziyang.bluelinebackend.entity.ArticlePo;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {

    @Select("Select t1.id as id,t1.title as title,t1.author as author_id,t2.username as author_name,t1.time as time,t1.content as content FROM article as t1 LEFT JOIN user t2 on t1.author=t2.id WHERE t1.id = #{id}")
    ArticlePo getArticleById(Integer id);

    @Select("SELECT t1.id as id,t1.title as title,t1.author as author_id,t2.username as author_name,t1.time as time,t1.title_img as title_img,t1.summary as summary FROM article as t1 LEFT JOIN user t2 on t1.author=t2.id WHERE t1.enable=1")
    List<ArticleSummaryPo> getSummaryByPage();

    @Select("SELECT id,title FROM article WHERE id<#{id} AND enable=1 ORDER BY id DESC LIMIT 0,1")
    PreviousAndNextArticlePo getPreviousArticle(Integer id);

    @Select("SELECT id,title FROM article WHERE id>#{id} AND enable=1 LIMIT 0,1")
    PreviousAndNextArticlePo getNextArticle(Integer id);

}
