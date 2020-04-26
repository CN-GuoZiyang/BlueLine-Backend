package top.guoziyang.bluelinebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleSummaryPo {

    private Integer id;
    private String title;
    private Integer authorId;
    private String authorName;
    private String titleImg;
    private String summary;

}
