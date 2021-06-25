package com.geoxus.util;

import com.geoxus.entities.TestEntity;

public class TestUtils {
    private TestUtils() {
        
    }

    public static String test(String str, TestEntity testEntity) {
        return str + " >>> " + testEntity.getTest();
    }
}
