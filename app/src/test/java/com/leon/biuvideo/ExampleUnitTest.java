package com.leon.biuvideo;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @Author Leon
 * @Time 2021/10/30
 * @Desc
 */
public class ExampleUnitTest {
    @Test
    public void test () {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("W" + i);
        }

        list.add(0, "首页");

        System.out.println(list);
    }
}
