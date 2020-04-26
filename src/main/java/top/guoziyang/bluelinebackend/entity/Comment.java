package top.guoziyang.bluelinebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Integer id;

    private Integer parentArticle;

    private Integer parentComment;

    private Integer author;

    private Timestamp time;

    private String content;

}
