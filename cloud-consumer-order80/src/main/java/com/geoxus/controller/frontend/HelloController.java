package com.geoxus.controller.frontend;

import cn.hutool.core.lang.Dict;
import com.geoxus.core.common.util.GXResultUtils;
import com.geoxus.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello/frontend")
@Slf4j
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/index")
    public GXResultUtils index(@RequestParam("name") String name) {
        log.info("AAAAA");
        return GXResultUtils.ok(Dict.create().set("name", name).set("age", 23).set("address", "四春生"));
    }
}
