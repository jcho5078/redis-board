package com.study.board.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "passWord")
    private String passWord;

    @Column(name = "nickName")
    private String nickName;

    @Column(name = "createTime")
    private String createTime;

    @Column(name = "isWithDraw")
    private String isWithDraw;

    @Column(name = "status")
    private String status;
}
