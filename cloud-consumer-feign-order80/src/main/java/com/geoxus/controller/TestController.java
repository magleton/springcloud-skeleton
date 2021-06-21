package com.geoxus.controller;

import com.geoxus.core.common.annotation.GXLoginAnnotation;
import com.geoxus.core.common.annotation.GXLoginUserAnnotation;
import com.geoxus.core.common.entity.GXUUserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/test")
    @GXLoginAnnotation
    public String test(@GXLoginUserAnnotation GXUUserEntity userEntity) {
        return "Hello World....";
    }
}
