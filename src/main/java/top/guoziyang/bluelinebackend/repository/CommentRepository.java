package top.guoziyang.bluelinebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.guoziyang.bluelinebackend.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> getCommentsByParentArticle(int articleId);

}
