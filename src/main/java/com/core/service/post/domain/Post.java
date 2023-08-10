package com.core.service.post.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long boardId;

    @ElementCollection
    private List<Long> commentIds = new ArrayList<>();

    public Post(Long boardId, Long commentId){
        this.boardId = boardId;
        addCommentId(commentId);
    }


    public void addCommentId(Long commentId){
        this.commentIds.add(commentId);
    }

    public void deleteCommentId(Long commentId){
        this.commentIds.remove(commentId);
    }
}