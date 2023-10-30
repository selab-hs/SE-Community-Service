package com.hs.selab.comment.domain.converter;

import com.hs.selab.auth.domain.UserDetail;
import com.hs.selab.comment.domain.Comment;
import com.hs.selab.comment.dto.request.CreateCommentRequest;
import com.hs.selab.comment.dto.response.ReadCommentResponse;

import com.hs.selab.member.domain.Member;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class CommentConverter {


    public Comment convertToEntity(CreateCommentRequest request, UserDetail userInfo) {
        return Comment.builder()
                .memberId(userInfo.getId())
                .postId(request.getPostId())
                .comment(request.getComment())
                .build();
    }

    public List<ReadCommentResponse> convertToReadCommentResponse(
        List<Comment> comments,
        Map<Long, Member> commentWriterMembers)
    {

       return comments.stream().map(

                comment ->{
                    var commentWriterMember = commentWriterMembers.get(comment.getMemberId()).getName().getName();
                    return new ReadCommentResponse(
                        comment.getId(),
                        comment.getMemberId(),
                        comment.getPostId(),
                        comment.getComment(),
                        commentWriterMember
                    );
                }
                ).collect(Collectors.toList());
    }
}
