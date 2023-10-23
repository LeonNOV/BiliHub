package com.leon.bilihub;

import com.leon.bilihub.utils.ValueUtils;

import org.junit.Test;

import java.util.Map;

/**
 * @Author Leon
 * @Time 2021/10/30
 * @Desc
 */
public class ExampleUnitTest {

    @Test
    public void test() {
//        System.out.println(String.valueOf(System.currentTimeMillis() / 1000));


        String wbiImg = "https://i0.hdslb.com/bfs/wbi/7cd084941338484aae1ad9425b84077c.png";
        String subImg = "https://i0.hdslb.com/bfs/wbi/4932caff0ff746eab6f01bf08b70ac45.png";

        String wbiKey = wbiImg.substring(wbiImg.lastIndexOf("/") + 1, wbiImg.lastIndexOf("."));
        String subKey = subImg.substring(subImg.lastIndexOf("/") + 1, subImg.lastIndexOf("."));
        String s = ValueUtils.genWbi(wbiKey,
                subKey,
//                Map.of(
//                        "oid", "364785592",
//                        "type", "1",
//                        "mode", "3",
//                        "pagination_str", "{\"offset\":\"\"}",
////                        "plat", "1",
////                        "seek_rpid", "",
////                        "web_location", "1315875",
//                        "wts", "1697790793"
//                ));
                Map.of("mid", "50329118", "wts", "1697987246"));
//
//                {
//                        foo: '114',
//                bar: '514',
//                zab: 1919810,
//                wts: 1684746387
//}
//                Map.of(
//                        "foo", 114,
//                        "bar", 514,
//                        "zab", 1919810,
//                        "wts", 1684746387
//                ));
        System.out.println(s);
        // 4ce663045ca53e60b5699dd77856f415

    }
}
