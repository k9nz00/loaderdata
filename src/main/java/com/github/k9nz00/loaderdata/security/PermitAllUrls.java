package com.github.k9nz00.loaderdata.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PermitAllUrls {

    public final static String API_URL_LOGIN = "/api/users/login";

    private static String[] permitUrls = new String[]{
            API_URL_LOGIN,
    };

    public static String[] getPermitUrls() {
        return permitUrls;
    }
}
