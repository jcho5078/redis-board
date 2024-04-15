package com.study.board.controller;


import com.study.board.aop.LoginCheck;
import com.study.board.dto.PostDTO;
import com.study.board.dto.UserDTO;
import com.study.board.dto.response.CommonResponse;
import com.study.board.service.PostService;
import com.study.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/posts")
@Log4j2
public class PostController {

    @Autowired
    private PostService postService;
    
    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<PostDTO>> registerPost(String accountId, @RequestBody PostDTO postDTO, HttpSession session) {
        postDTO.setSessionId(session.getId());
        postService.register(accountId, postDTO);
        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "registerPost", postDTO);
        return ResponseEntity.ok(commonResponse);
    }

    @GetMapping("my-posts")
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<List<PostDTO>>> myPostInfo(String accountId) {
        UserDTO memberInfo = userService.getUserInfo(accountId);
        List<PostDTO> postDTOList = postService.getMyPosts(memberInfo.getUserNo());
        CommonResponse commonResponse = new CommonResponse(HttpStatus.OK, "SUCCESS", "myPostInfo", postDTOList);
        return ResponseEntity.ok(commonResponse);
    }

    @GetMapping("posts")
    public ResponseEntity<CommonResponse<List<PostDTO>>> getPosts(PostDTO postDTO, HttpSession session){
        String sessionId = session.getId();
        postDTO.setSessionId(sessionId);

        List<PostDTO> postDTOList = postService.getPosts(postDTO);
        CommonResponse commonResponse = new CommonResponse(HttpStatus.OK, "SUCCESS", "getPosts", postDTOList);
        return ResponseEntity.ok(commonResponse);
    }

    @PatchMapping("{postNo}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<PostDTO>> updatePosts(String accountId,
                                                                   @PathVariable(name = "postNo") int postNo,
                                                                   @RequestBody PostDTO postDTO) {
        UserDTO memberInfo = userService.getUserInfo(accountId);
        postDTO.setPostNo(postNo);
        postDTO.setUserNo(memberInfo.getUserNo());
        postDTO.setUpdateTime(new Date());

        postService.updatePost(postDTO);
        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "updatePosts", postDTO);
        return ResponseEntity.ok(commonResponse);
    }

    @DeleteMapping("{postNo}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<PostDTO>> deleteposts(String accountId,
                                                                         @PathVariable(name = "postNo") int postNo,
                                                                         @RequestBody PostDTO postDeleteRequest) {
        UserDTO memberInfo = userService.getUserInfo(accountId);
        postService.deletePost(memberInfo.getUserNo(), postNo);
        CommonResponse commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "deleteposts", postDeleteRequest);
        return ResponseEntity.ok(commonResponse);
    }
}
