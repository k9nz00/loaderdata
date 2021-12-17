package com.github.k9nz00.loaderdata.service.impl;

import com.github.k9nz00.loaderdata.dao.type.UserMetaDao;
import com.github.k9nz00.loaderdata.security.Authorities;
import com.github.k9nz00.loaderdata.service.UserMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserMetaServiceImpl  implements UserMetaService {

    private final UserMetaDao userMetaDao;

    @Autowired
    public UserMetaServiceImpl(UserMetaDao userMetaDao) {
        this.userMetaDao = userMetaDao;
    }

    @Override
    public long getCountUsers() {
        return userMetaDao.getCountUsers();
    }

    @Override
    public Collection<String> getAuthorityList() {
        return Authorities.getAllAuthorities();
    }
}
