package com.core.service.common.header;

import javax.servlet.http.HttpServletRequest;

public class HeaderUtil {
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    /**
     * @brief      * token 헤더 조회
     * @param      * HttpServletRequest : 요청 서블릿
     * @return     * 토큰이 있다면 토큰 헤더 String, 없다면 null
     */

    public static String getAccessToken(HttpServletRequest request) {
        String headerValue = request.getHeader(HEADER_AUTHORIZATION);

        if (headerValue != null && headerValue.startsWith(TOKEN_PREFIX)) {
            return headerValue.substring(TOKEN_PREFIX.length());
        }

        return null;
    }
}
