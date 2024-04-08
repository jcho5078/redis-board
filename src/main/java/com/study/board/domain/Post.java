package com.study.board.domain;

import jakarta.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String isAdmin;

    @Column
    private String contents;

    @Column
    private String createTime;

    @Column
    private String views;

    @Column
    private String categoryId;

    @Column
    private String userId;

    @Column
    private String fileId;

    @Column
    private String updateTime;
}
