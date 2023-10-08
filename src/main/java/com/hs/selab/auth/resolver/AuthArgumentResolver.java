package com.hs.selab.auth.resolver;

import com.hs.selab.auth.domain.UserDetail;
import com.hs.selab.auth.infrastructure.annotation.AuthMember;
import com.hs.selab.auth.infrastructure.authentication.DefaultAuthenticationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {
    private final DefaultAuthenticationStrategy defaultAuthenticationStrategy;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Objects.nonNull(parameter.getParameterAnnotation(AuthMember.class));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer
            , NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        var httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        var userData = userInfo(httpServletRequest);
        if (parameter.getParameterType() == Optional.class) {
            return userData;
        }
        return userData.orElseThrow(NullPointerException::new);
    }

    private Optional<UserDetail> userInfo(HttpServletRequest request){
        return Optional.of(defaultAuthenticationStrategy.get(request).getUserDetail());
    }
}
