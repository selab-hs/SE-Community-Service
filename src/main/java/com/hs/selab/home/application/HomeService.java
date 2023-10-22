package com.hs.selab.home.application;

import com.hs.selab.auth.domain.UserDetail;
import com.hs.selab.home.dto.response.HomeResponse;
import com.hs.selab.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final PostService postService;

    public HomeResponse getHome(UserDetail userInfo, Pageable pageable) {
        // 공지사항을 제외한 데이터
        var posts = postService.findAll(pageable, 2L);



        // 공지사항
        var noticePosts = postService.findTop5NoticePosts();

        return new HomeResponse(posts, noticePosts);
    }
}
