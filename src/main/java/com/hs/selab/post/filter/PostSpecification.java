package com.hs.selab.post.filter;

import com.hs.selab.post.domain.Post;
import org.springframework.data.jpa.domain.Specification;

public class PostSpecification {
    public static Specification<Post> equalsTitle(String title) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("title"),"%"+title+"%");
    }
}
