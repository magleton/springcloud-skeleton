package com.geoxus.controller;

import com.geoxus.shiro.annotation.GXLoginAnnotation;
import com.geoxus.shiro.annotation.GXLoginUserAnnotation;
import com.geoxus.shiro.entities.GXUUserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/frontend")
public class TestController {
    @GetMapping("/test")
    @GXLoginAnnotation
    public String test(@GXLoginUserAnnotation GXUUserEntity userEntity) {
        return "Hello World....";
    }
}
