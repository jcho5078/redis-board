package com.study.board.domain.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "t_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_no")
    private long postNo;

    @Column()
    private String name;

    @Column()
    private int isAdmin;

    @Column()
    private String contents;

    @Column()
    private Date createTime;

    @Column()
    private int views;

    @Column()
    private int categoryId;

    @Column()
    private int userId;

    @Column()
    private int fileId;

    @Column()
    private Date updateTime;
}
