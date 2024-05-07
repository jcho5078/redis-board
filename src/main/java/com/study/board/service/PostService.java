package com.study.board.service;

import com.study.board.dto.PostDTO;

import java.util.List;

public interface PostService {
    void register(String id, PostDTO postDTO);

    List<PostDTO> getMyPosts(long accountId);

    List<PostDTO> getMyPosts2(long accountId);

    List<PostDTO> getPosts(PostDTO postDTO);

    void updatePost(PostDTO postDTO);

    void deletePost(long userNo, long postNo);
}
