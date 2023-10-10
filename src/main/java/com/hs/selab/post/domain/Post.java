package com.hs.selab.post.domain;


import com.fasterxml.jackson.databind.ser.Serializers.Base;
import com.hs.selab.common.domain.BaseEntity;
import com.hs.selab.post.dto.request.UpdatePostRequest;
import javax.persistence.Column;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private Long memberId;

    private Long boardId;

    private String title;

    private String content;


    public void update(UpdatePostRequest request){
        this.title = request.getTitle();
        this.content = request.getContent();
    }
}