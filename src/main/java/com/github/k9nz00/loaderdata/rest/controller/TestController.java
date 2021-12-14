package com.github.k9nz00.loaderdata.rest.controller;

import com.github.k9nz00.loaderdata.security.Authorities;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
public class TestController {

    @GetMapping("/test")
    @Secured(Authorities.MANAGE_SETTINGS)
    public Collection<String> getTestData() {
        return Collections.singletonList("tests");
    }
}
