package com.hs.selab.post.application;

import com.hs.selab.auth.domain.UserDetail;
import com.hs.selab.error.exception.board.NonExistentBoardException;
import com.hs.selab.error.exception.member.UnauthorizedAccessException;
import com.hs.selab.member.domain.vo.RoleType;
import com.hs.selab.post.domain.Post;
import com.hs.selab.post.domain.PostView;
import com.hs.selab.post.domain.converter.PostConverter;
import com.hs.selab.post.dto.request.CreatePostRequest;
import com.hs.selab.post.dto.request.UpdatePostRequest;
import com.hs.selab.post.dto.response.ReadAllPostResponse;
import com.hs.selab.post.dto.response.ReadPostResponse;
import com.hs.selab.post.event.PostViewEvent;
import com.hs.selab.post.filter.PostSpecification;
import com.hs.selab.post.infrastructure.PostRepository;
import com.hs.selab.post.infrastructure.PostViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.hs.selab.error.dto.ErrorMessage.NON_EXISTENT_BOARD_EXCEPTION;
import static com.hs.selab.error.dto.ErrorMessage.UNAUTHORIZED_ACCESS_EXCEPTION;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostViewRepository postViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final PostConverter converter;

    @Transactional
    public Long create(
            CreatePostRequest request,
            UserDetail userInfo,
            Long boardId
    ) {
        if (!userInfo.getRoleType().equals(RoleType.USER)) {
            throw new UnauthorizedAccessException(
                    UNAUTHORIZED_ACCESS_EXCEPTION,
                    "권한이 없는 접근입니다."
            );
        }
        var post = converter.convertToPostEntity(request, userInfo, boardId);
        postRepository.save(post);

        return post.getId();
    }

    @Async
    @Transactional
    public void createPostView(Long postId) {
        var postView = converter.convertToEventPostView(postId);
        postViewRepository.save(postView);
    }

    @Transactional
    public void update(Long postId, UpdatePostRequest request, UserDetail userInfo) {
        if (!(postRepository.existsByIdAndMemberId(postId, userInfo.getId()))) {
            throw new UnauthorizedAccessException(
                    UNAUTHORIZED_ACCESS_EXCEPTION,
                    "권한이 없는 접근입니다."
            );
        }
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new NonExistentBoardException(
                                NON_EXISTENT_BOARD_EXCEPTION,
                                "업데이트 단일 게시판 조회 실패"
                        )
                );
        post.update(request);
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public ReadPostResponse get(
            Long postId,
            UserDetail userInfo
    ) {
        if (!userInfo.getRoleType().equals(RoleType.USER)) {
            throw new UnauthorizedAccessException(
                    UNAUTHORIZED_ACCESS_EXCEPTION,
                    "권한이 없는 접근입니다."
            );
        }

        return converter.convertToReadPostResponse
                (
                        postRepository.findById(postId)
                                .orElseThrow(
                                        () -> new NonExistentBoardException(
                                                NON_EXISTENT_BOARD_EXCEPTION,
                                                "단일 게시판 조회 실패")),
                        postViewRepository.findByPostId(postId)
                                .orElseThrow(() -> new NonExistentBoardException(
                                        NON_EXISTENT_BOARD_EXCEPTION,
                                        "업데이트 단일 게시판 조회 실패"))
                                .getPostView()
                );
    }

    @Transactional(readOnly = true)
    public Page<ReadAllPostResponse> findAll(Pageable pageable, Long boardId) {
        var posts = postRepository.findAllByBoardId(boardId, pageable);
        var postIds = posts.stream().map(Post::getId).collect(Collectors.toList());

        var postViews = postViewRepository.findAllById(postIds)
                .stream()
                .collect(Collectors.toMap(PostView::getPostId, Function.identity()));

        return converter.convertToReadAllPostResponse(posts, postViews);
    }

    public List<ReadAllPostResponse> findTop5NoticePosts() {
        var posts = postRepository.findTop5ByBoardIdOrderByIdDesc(1L);
        var postIds = posts.stream().map(Post::getId).collect(Collectors.toList());

        var postViews = postViewRepository.findAllById(postIds)
                .stream()
                .collect(Collectors.toMap(PostView::getPostId, Function.identity()));

        return converter.convertToListReadAllPostResponse(posts, postViews);
    }


    @Transactional(readOnly = true)
    public Page<ReadAllPostResponse> getSearchAll(Pageable pageable, String title) {
        Specification<Post> spec = (root, query, criteriaBuilder) -> null;
        spec = spec.and(PostSpecification.equalsTitle(title));
        var posts = postRepository.findAll(spec, pageable);
        var postIds = posts.stream().map(Post::getId).collect(Collectors.toList());

        var postViews = postViewRepository.findAllById(postIds)
                .stream()
                .collect(Collectors.toMap(PostView::getPostId, Function.identity()));

        return converter.convertToReadAllPostResponse(posts, postViews);
    }

    @Transactional(readOnly = true)
    public List<ReadAllPostResponse> getAllList(Long boardId) {
        var posts = postRepository.findAllByBoardId(boardId);
        var postIds = posts.stream().map(Post::getId).collect(Collectors.toList());

        var postViews = postViewRepository.findAllById(postIds)
                .stream()
                .collect(Collectors.toMap(PostView::getPostId, Function.identity()));

        return converter.convertToListReadAllPostResponse(posts, postViews);
    }


    @Transactional
    public void updatePostView(Long postId) {
        applicationEventPublisher.publishEvent(new PostViewEvent(postId));
    }

    @Transactional
    public void delete(Long postId, UserDetail userInfo) {
        if (!postRepository.existsByIdAndMemberId(postId, userInfo.getId())) {
            throw new UnauthorizedAccessException(
                    UNAUTHORIZED_ACCESS_EXCEPTION,
                    "권한이 없는 접근입니다."
            );
        }
        postRepository.deleteById(postId);
        postViewRepository.deleteById(postId);
    }
}
