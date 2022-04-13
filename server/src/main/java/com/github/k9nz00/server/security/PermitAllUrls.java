package com.github.k9nz00.server.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PermitAllUrls {

    public static final String API_URL_LOGIN = "/api/users/login";
    public static final String PUBLIC_API_URL_CREATE_USER = "/api/users/register";
    public static final String PUBLIC_API_URL_CURRENT_USER = "/api/users/currentUser";

    public static String[] getPermitUrls() {
        return new String[]{
                API_URL_LOGIN,
                PUBLIC_API_URL_CREATE_USER,
                PUBLIC_API_URL_CURRENT_USER
        };
    }
}
