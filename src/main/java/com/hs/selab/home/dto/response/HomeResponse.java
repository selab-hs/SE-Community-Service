package com.hs.selab.home.dto.response;

import com.hs.selab.post.dto.response.ReadAllPostResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
public class HomeResponse {
    private Page<ReadAllPostResponse> posts;
    private List<ReadAllPostResponse> noticePosts;
}
