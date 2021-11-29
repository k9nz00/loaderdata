package com.semka.loaderdata.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Authorities {

    public static final String ACCESS_IS_DENIED_ERROR = "Access is denied";

    public static final String MANAGE_USERS = "ROLE_MANAGE_USERS";
    public static final String MANAGE_SETTINGS = "ROLE_MANAGE_SETTINGS";

    public static final String VIEW_ALL_QUERIES_PAGES = "ROLE_VIEW_ALL_PAGES";
    public static final String VIEW_SOME_PAGES= "ROLE_VIEW_SOME_PAGES";

    public static final Collection<String> VIEW_AUTHORITIES = List.of(VIEW_ALL_QUERIES_PAGES, VIEW_SOME_PAGES);
}
