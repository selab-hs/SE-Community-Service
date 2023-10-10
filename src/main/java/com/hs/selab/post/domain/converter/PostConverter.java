package com.hs.selab.post.domain.converter;

import com.hs.selab.auth.domain.UserDetail;
import com.hs.selab.common.util.dto.ListToPageConverter;
import com.hs.selab.post.domain.Post;
import com.hs.selab.post.domain.PostView;
import com.hs.selab.post.dto.request.CreatePostRequest;
import com.hs.selab.post.dto.response.ReadAllPostResponse;
import com.hs.selab.post.dto.response.ReadPostResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {
    public Post convertToPostEntity(CreatePostRequest request, UserDetail userInfo) {
        return Post.builder()
            .memberId(userInfo.getId())
            .title(request.getTitle())
            .content(request.getContent())
            .build();
    }
    public PostView convertToEventPostView(Long postId){
        return PostView.builder()
            .postId(postId)
            .postView(0L)
            .build();
    }


    public ReadPostResponse convertToReadPostResponse(Post post, Long postView) {
        return ReadPostResponse.builder()
            .id(post.getId())
            .memberId(post.getMemberId())
            .title(post.getTitle())
            .content(post.getContent())
            .view(postView)
            .build();
    }

    public Page<ReadAllPostResponse> convertToReadAllPostResponse(
        List<Post> posts,
        List<PostView> postViews,
        Pageable pageable
    ){
        List<ReadAllPostResponse> result = new ArrayList<>();
        for(int i =0; i<posts.size();i++){
            var post = posts.get(i);
            var postView = postViews.get(i);

            result.add(
                ReadAllPostResponse.builder()
                    .id(post.getId())
                    .memberId(post.getMemberId())
                    .title(post.getTitle())
                    .content(post.getTitle())
                    .view(postView.getPostView())
                    .build()
            );
        }
        return ListToPageConverter.convert(
            result,
            pageable.getPageNumber(),
            pageable.getPageSize()
        );
    }
}