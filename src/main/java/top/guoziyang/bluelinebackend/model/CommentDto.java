package top.guoziyang.bluelinebackend.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Data
public class CommentDto {

    private Integer id;
    private Integer parentComment;
    private Integer authorId;
    private String authorName;
    private Timestamp time;
    private String content;
    private List<CommentDto> childrenComments;

}
