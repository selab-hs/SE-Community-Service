package com.hs.selab.post.domain.converter;

import com.hs.selab.auth.domain.UserDetail;
import com.hs.selab.post.domain.Post;
import com.hs.selab.post.domain.PostView;
import com.hs.selab.post.dto.request.CreatePostRequest;
import com.hs.selab.post.dto.response.ReadAllPostResponse;
import com.hs.selab.post.dto.response.ReadPostResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PostConverter {
    public Post convertToPostEntity(CreatePostRequest request, UserDetail userInfo, Long boardId) {
        return Post.builder()
                .boardId(boardId)
                .memberId(userInfo.getId())
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }

    public PostView convertToEventPostView(Long postId) {
        return PostView.builder()
                .postId(postId)
                .postView(0L)
                .build();
    }


    public ReadPostResponse convertToReadPostResponse(Post post, Long postView) {
        return ReadPostResponse.builder()
                .id(post.getId())
                .boardId(post.getBoardId())
                .memberId(post.getMemberId())
                .title(post.getTitle())
                .content(post.getContent())
                .view(postView)
                .build();
    }

    public Page<ReadAllPostResponse> convertToReadAllPostResponse(
            Page<Post> posts,
            Map<Long, PostView> postViews
    ) {
        return posts.map(p -> {
                    var view = 0L;

                    if (postViews.get(p.getId()) != null) {
                        view = postViews.get(p.getId()).getPostView();
                    }

                    return new ReadAllPostResponse(
                            p.getId(),
                            p.getBoardId(),
                            p.getMemberId(),
                            p.getTitle(),
                            p.getContent(),
                            view
                    );
                }
        );
    }
}
