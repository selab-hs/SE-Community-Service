package com.core.service.common.header;

import javax.servlet.http.HttpServletRequest;

public class HeaderUtil {
    public final static String SELAB_AUTH_HEADER_KEY = "X-SELAB-AUTH-TOKEN";

    /**
     * @param * HttpServletRequest : 요청 서블릿
     * @return * 토큰이 있다면 토큰 헤더 String, 없다면 null
     * @brief * token 헤더 조회
     */

    public static String getAccessToken(HttpServletRequest request) {
        return request.getHeader(SELAB_AUTH_HEADER_KEY);
    }
}
