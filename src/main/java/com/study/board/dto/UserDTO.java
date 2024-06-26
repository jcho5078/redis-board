package com.study.board.dto;

import com.study.board.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO {
    public enum Status {
        DEFAULT, ADMIN, DELETED
    }
    private Long userNo;
    private String userId;
    private String password;
    private String nickName;
    private boolean isAdmin;
    private Date createTime;
    private boolean isWithDraw;
    private Status status;
    private Date updateTime;

    public UserDTO(String userId, String password, String name, String phone, String address, Status status, Date createTime, Date updateTime, boolean isAdmin) {
        this.userId = userId;
        this.password = password;
        this.nickName = name;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isAdmin = isAdmin;
    }

    public static boolean hasNullDataBeforeSignup(UserDTO userDTO) {
        return userDTO.getUserId() == null || userDTO.getPassword() == null
                || userDTO.getNickName() == null;
    }

    public User toEntity(){
        User user = new User().builder()
                .userId(this.userId)
                .password(this.password)
                .nickname(this.nickName)
                .createTime(this.getCreateTime())
                .isWithDraw(this.isWithDraw)
                .status(this.status)
                .build();

        return user;
    }
}