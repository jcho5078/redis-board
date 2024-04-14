package com.study.board.service.impl;

import com.study.board.domain.post.PostRepository;
import com.study.board.dto.PostDTO;
import com.study.board.dto.UserDTO;
import com.study.board.mapper.PostMapper;
import com.study.board.mapper.UserProfileMapper;
import com.study.board.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @CacheEvict(value="getProducts", allEntries = true)
    @Override
    public void register(String id, PostDTO postDTO) {
        UserDTO memberInfo = userProfileMapper.getUserProfile(id);
        postDTO.setUserNo(memberInfo.getUserNo());
        postDTO.setCreateTime(new Date());

        if (memberInfo != null) {
            postRepository.save(postDTO.toEntity());
        } else {
            log.error("register ERROR! {}", postDTO);
            throw new RuntimeException("register ERROR! post 등록 메서드 확인요망\n" + "Params : " + postDTO);
        }
    }

    @Override
    @Cacheable(value = "getProducts", key = "'getProducts' + #accountId")
    public List<PostDTO> getMyPosts(long accountId) {
        List<PostDTO> postDTOList = postMapper.selectMyPosts(accountId);
        return postDTOList;
    }

    @Override
    @Cacheable(value = "getProducts", key = "'getProducts' + #postDTO.getSessionId()")
    public List<PostDTO> getPosts(PostDTO postDTO) {
        List<PostDTO> postDTOList = postMapper.selectPosts(postDTO);
        return postDTOList;
    }


    @Override
    public void updatePost(PostDTO postDTO) {
        if (postDTO != null && postDTO.getPostNo() != 0 && postDTO.getUserNo() != 0) {
            postMapper.updatePost(postDTO);
        } else {
            log.error("updateProducts ERROR! {}", postDTO);
            throw new RuntimeException("updateProducts ERROR! post 수정 메서드 확인요망\n" + "Params : " + postDTO);
        }
    }

    @Override
    public void deletePost(long userNo, long postNo) {
        if (userNo != 0 && postNo != 0) {
            postMapper.deletePost(userNo, postNo);
        } else {
            log.error("delete Post ERROR! {}", postNo);
            throw new RuntimeException("update Post ERROR! post 삭제 메서드 확인요망\n" + "Params : " + postNo);
        }
    }
}