package com.core.service.comment.domain;

import com.core.service.comment.dto.request.UpdateCommentRequest;
import com.core.service.common.domain.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private Long boardId;

    private String comment;

    public void update(UpdateCommentRequest updateCommentRequest){
        this.comment = updateCommentRequest.getComment();
    }
}
