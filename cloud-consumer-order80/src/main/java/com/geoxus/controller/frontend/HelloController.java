package com.geoxus.controller.frontend;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.geoxus.core.common.util.GXResultUtils;
import com.geoxus.dto.TestDTO;
import com.geoxus.entities.TestEntity;
import com.geoxus.mapstruct.TestMapStruct;
import com.geoxus.service.HelloService;
import com.geoxus.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/hello/frontend")
@Slf4j
public class HelloController {
    @Resource
    private HelloService helloService;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private TestService testService;

    @Resource
    private TestMapStruct testMapStruct;

    @PostMapping("/index")
    public GXResultUtils index() {
        log.info("AAAAA");
        return GXResultUtils.ok("");
    }

    @PostMapping("/create")
    public GXResultUtils index(@RequestBody TestDTO testDTO) {
        log.info("AAAAA");
        TestEntity testEntity = testMapStruct.dtoToEntity(testDTO);
        testService.save(testEntity);
        return GXResultUtils.ok("");
    }

    @PostMapping("/get")
    public GXResultUtils index(@RequestBody Dict param) {
        Integer id = param.getInt("id");
        TestEntity entity = testService.getById(id);
        return GXResultUtils.ok().putData(entity);
    }

    @PostMapping("/getList")
    public GXResultUtils getList() {
        List<TestEntity> list = testService.list(new QueryWrapper<TestEntity>().le("id", 20));
        return GXResultUtils.ok().putData(list);
    }
}
