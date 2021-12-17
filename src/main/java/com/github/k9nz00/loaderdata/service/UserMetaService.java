package com.github.k9nz00.loaderdata.service;

import com.github.k9nz00.loaderdata.security.Authorities;

import java.util.Collection;

public interface UserMetaService {

    long getCountUsers();

    Collection<String> getAuthorityList();
}
