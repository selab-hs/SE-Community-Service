package com.hs.selab.post.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostRequest {
    private String title;
    private String content;
}
