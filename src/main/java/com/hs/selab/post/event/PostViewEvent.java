package com.hs.selab.post.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostViewEvent {
    private Long postId;
}