package com.hs.selab.auth.filter;

import com.hs.selab.auth.infrastructure.LocalContextHolder;
import com.hs.selab.auth.token.TokenProvider;
import com.hs.selab.common.header.HeaderUtil;
import com.hs.selab.common.util.MapperUtil;
import com.hs.selab.error.dto.ErrorMessage;
import com.hs.selab.error.dto.ErrorResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        var token = HeaderUtil.getAccessToken((HttpServletRequest) request);

        if (token != null) {
            if(!tokenProvider.validateDateToken(token)) {
                jwtExceptionHandler(response);
                chain.doFilter(request, response);
            }

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

    public void jwtExceptionHandler(ServletResponse response){
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        var errorResponse = ErrorResponseDto.of(ErrorMessage.EXPIRED_JWT_EXCEPTION);

        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

        try{
            httpResponse.getWriter().write(MapperUtil.mapper().writeValueAsString(errorResponse));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
