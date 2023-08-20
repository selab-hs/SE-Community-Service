package com.core.service.auth.filter;

import com.core.service.auth.infrastructure.LocalContextHolder;
import com.core.service.auth.token.TokenProvider;
import com.core.service.common.header.HeaderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        var token = HeaderUtil.getAccessToken((HttpServletRequest) request);

        if (token != null && tokenProvider.validateToken(token)) {
            var authentication = tokenProvider.getAuthentication(token);
            LocalContextHolder.setContext(authentication);
        }
        if(token == null){
            request.setAttribute("noToken", Optional.ofNullable(null));
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LocalContextHolder.remove();
    }
}
