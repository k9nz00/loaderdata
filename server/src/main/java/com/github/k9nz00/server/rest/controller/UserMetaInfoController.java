package com.github.k9nz00.server.rest.controller;

import com.github.k9nz00.server.service.UserMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/usersMeta")
public class UserMetaInfoController {

    private final UserMetaService userMetaService;

    @Autowired
    public UserMetaInfoController(UserMetaService userMetaService) {
        this.userMetaService = userMetaService;
    }

    @GetMapping("/usersCount")
    public long getCountRegisteredUsers() {
        return userMetaService.getCountUsers();
    }

    @GetMapping("/authorities")
    public Collection<String> getAuthoritiesList() {
        return userMetaService.getAuthorityList();
    }
}
