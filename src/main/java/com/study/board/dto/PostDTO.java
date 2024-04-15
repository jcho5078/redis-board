package com.study.board.dto;

import com.study.board.domain.post.Post;
import com.study.board.domain.user.User;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private long postNo;
    private String title;
    private boolean isAdmin;
    private String contents;
    private Date createTime;
    private int views;
    private int categoryId;
    private long userNo;
    private int fileId;
    private Date updateTime;

    private String sessionId;

    public Post toEntity(){
        Post post = new Post().builder()
                .title(this.title)
                .isAdmin(this.isAdmin)
                .contents(this.contents)
                .createTime(this.createTime)
                .views(this.views)
                .categoryId(this.categoryId)
                .userNo(new User(this.userNo))
                .fileId(this.fileId)
                .build();

        return post;
    }
}