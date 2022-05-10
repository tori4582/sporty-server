package edu.uit.grp7.sportyserver.common;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationHelpers {

    private static final String BEARER_PREFIX = "Bearer ";

    public static final String getBearerToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        return (
                (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(BEARER_PREFIX))
                        ? authorizationHeader.substring(BEARER_PREFIX.length())
                        : null
        );


    }
}
