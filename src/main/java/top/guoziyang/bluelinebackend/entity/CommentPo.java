package top.guoziyang.bluelinebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentPo {

    private Integer id;
    private Integer parentComment;
    private Integer authorId;
    private Timestamp time;
    private String content;
    private String authorName;

}
