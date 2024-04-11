package com.study.board.mapper;

import com.study.board.dto.PostDTO;
import com.study.board.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    public List<PostDTO> selectMyPosts(long accountId);

    public void updatePost(PostDTO postDTO);

    public void deletePost(long userNo, long postNo);
}
