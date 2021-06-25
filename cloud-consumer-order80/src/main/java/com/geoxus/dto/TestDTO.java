package com.geoxus.dto;

import com.geoxus.core.common.dto.GXBaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TestDTO extends GXBaseDTO {
    private String content;

    private String test;

    private List<String> roster = new ArrayList<>();
}

