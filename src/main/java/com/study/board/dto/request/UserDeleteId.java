package com.study.board.dto.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class UserDeleteId {
    @NonNull
    private String userNo;
    @NonNull
    private String password;
}