package com.semka.loaderdata.rest.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

import static com.semka.loaderdata.security.Authorities.MANAGE_SETTINGS;

@RestController
public class TestController {

    @GetMapping("/test")
    @Secured(MANAGE_SETTINGS)
    public Collection<String> getTestData() {
        return Collections.singletonList("tests");
    }
}
