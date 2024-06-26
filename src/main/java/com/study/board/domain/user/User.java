package com.study.board.domain.user;

import com.study.board.dto.UserDTO;
import com.study.board.util.BooleanConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

@Entity
@Table(name = "t_user", indexes = {
        @Index(name = "idx_t_user_IdPassword", columnList = "user_id, password")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@RedisHash("t_user")
public class User {

    public User(long userNo){
        this.userNo = userNo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private long userNo;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "is_with_draw")
    @Convert(converter = BooleanConverter.class)
    @ColumnDefault(value = "'Y'")
    private boolean isWithDraw;

    @Column(name = "status", length = 15, columnDefinition = "varchar(15) default 'DEFAULT'", insertable = false)
    @Enumerated(EnumType.STRING)
    private UserDTO.Status status;
}
