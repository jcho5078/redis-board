package com.study.board.domain.post;

import com.study.board.domain.user.User;
import com.study.board.util.BooleanConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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
    @Convert(converter = BooleanConverter.class)
    @ColumnDefault(value = "'Y'")
    private boolean isAdmin;

    @Column()
    private String contents;

    @Column()
    private Date createTime;

    @Column()
    private int views;

    @Column()
    private long categoryId;

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private User userNo;

    @Column()
    private long fileId;

    @Column()
    private Date updateTime;
}
