package com.leon.biuvideo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leon
 * @Time 2021/10/30
 * @Desc
 */
public class ExampleUnitTest {
    @Test
    public void test () {
        List<Integer> listA = new ArrayList<>();
        listA.add(3);
        listA.add(1 + 3);
        listA.add(2 + 3);

        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.addAll(0, listA);

        System.out.println("awdawdada");
    }
}
