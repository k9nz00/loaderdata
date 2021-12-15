package com.github.k9nz00.loaderdata.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PermitAllUrls {

    public static final String API_URL_LOGIN = "/api/users/login";

    public static String[] getPermitUrls() {
        return new String[]{
                API_URL_LOGIN,
        };
    }
}
