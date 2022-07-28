package com.sparta.post.service;

import com.sparta.post.domain.Post;
import com.sparta.post.domain.PostRepository;
import com.sparta.post.domain.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        post.update(requestDto);
        return post.getId();
    }

    public Boolean checkPw(Long id, String password) throws Exception {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return post.getPassword().equals(password);
    }

    public Boolean delete(Long id) throws Exception {
        postRepository.deleteById(id);
        return true;
    }

}