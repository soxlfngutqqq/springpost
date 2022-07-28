package com.sparta.post.controller;

import com.sparta.post.domain.Post;
import com.sparta.post.domain.PostRepository;
import com.sparta.post.domain.PostRequestDto;
import com.sparta.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;

    private final PostService postService;

//    private final PasswordConfirm passwordConfirm;

    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    @PostMapping("/api/post/{id}")
    public boolean checkPassword(@PathVariable Long id, @RequestBody PostRequestDto requestDto) throws Exception {
        return postService.checkPw(id, requestDto.getPassword());
    }


    @GetMapping("/api/posts")
    public List<Post> getPost() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping("/api/post/{id}")
    public Optional<Post> readWrite(@PathVariable Long id) {
        return postRepository.findById(id);
    }

    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }

    @DeleteMapping("/api/post/password/{id}")
    public boolean deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) throws Exception {
        if (postService.checkPw(id, requestDto.getPassword())) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }



}
