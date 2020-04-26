package top.guoziyang.bluelinebackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.guoziyang.bluelinebackend.entity.CommentPo;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    @Select("SELECT t1.id as id,t1.parent_comment as parent_comment,t1.author as author_id,t1.time as time,t1.content as content,t2.username as author_name from comment t1 left join user t2 on t2.id=t1.author where t1.parent_article=#{articleId}")
    List<CommentPo> getCommentsByParentArticle(Integer articleId);

}
