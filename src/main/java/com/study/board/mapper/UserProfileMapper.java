package com.study.board.mapper;

import com.study.board.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserProfileMapper {
    public UserDTO getUserProfile(@Param("userNo") String userNo);

    int insertUserProfile(@Param("userId") String userId, @Param("password") String password
            , @Param("name") String name, @Param("phone") String phone
            , @Param("address") String address);

    int updateUserProfile(@Param("userId") String userId, @Param("password") String password
            , @Param("name") String name, @Param("phone") String phone
            , @Param("address") String address);

    int deleteUserProfile(@Param("userNo") String userNo);

    public UserDTO findByUserNoAndPassword(@Param("userNo") String userNo,
                                           @Param("password") String password);

    public UserDTO findByUserIdAndPassword(@Param("userId") String userId,
                                           @Param("password") String password);

    int idCheck(String id);

    public int updatePassword(UserDTO userDTO);
}
