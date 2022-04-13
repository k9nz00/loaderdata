package com.github.k9nz00.server.service;

import java.util.Collection;

public interface UserMetaService {

    long getCountUsers();

    Collection<String> getAuthorityList();
}
