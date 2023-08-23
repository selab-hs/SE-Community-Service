package com.core.service.board.domain;

import com.core.service.board.dto.request.UpdateBoardRequest;
import com.core.service.common.domain.BaseEntity;
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
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Board extends BaseEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private Long memberId;

    private String title;

    private String content;

    private Long viewCount;

    public void update(UpdateBoardRequest request){
        this.title = request.getTitle();
        this.content = request.getContent();
    }

    public void updateView(){
        this.viewCount++;
    }
}