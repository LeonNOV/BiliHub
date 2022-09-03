package com.leon.bilihub.utils;

import android.text.Html;
import android.text.Spanned;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * @Author Leon
 * @Time 2020/10/27
 * @Desc 数值工具类
 */
public class ValueUtils {
    /**
     * 生成以万结尾的字符串，小于1万则直接返回
     *
     * @param number 需要格式化的数
     * @return 返回结果
     */
    public static String generateCN(long number) {
        DecimalFormat decimalFormat = new DecimalFormat("#.0");

        //确定数量区间
        if (number < 10000) {
            return String.valueOf(number);
        } else if (number >= 100000000) {
            return decimalFormat.format((double) number / 100000000) + "亿";
        } else {
            return decimalFormat.format((double) number / 10000) + "万";
        }
    }

    /**
     * 秒转换为一定格式的长度
     *
     * @param length 需要格式化的长度
     * @return 返回结果
     */
    public static String toMediaDuration(int length) {
        int minute = length / 60;
        int second = length % 60;

        String minuteStr = String.valueOf(minute);
        String secondStr = String.valueOf(second);

        minuteStr = minuteStr.length() < 2 ? "0" + minuteStr : minuteStr;
        secondStr = secondStr.length() < 2 ? "0" + secondStr : secondStr;

        return minuteStr + ":" + secondStr;
    }

    /**
     * 对关键字进行处理
     *
     * @param origin 原文
     * @return Spanned
     */
    public static Spanned keywordTrim(String origin) {
        return Html.fromHtml(origin.replaceAll("<em class=\"keyword\">", "<font color=#fb7299>").replaceAll("</em>", "</font>"), Html.FROM_HTML_MODE_COMPACT);
    }

    /**
     * 字节大小转换
     *
     * @param size 需要转换的大小(单位：byte)
     * @return 返回转换后的数据
     */
    public static String sizeFormat(long size, boolean withSuffix) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        if (size > 1024) {
            //转换为KB
            double kb = (double) size / 1024;

            if (kb > 1024) {
                double mb = kb / 1024;

                if (mb > 1024) {
                    double gb = mb / 1024;

                    if (gb > 1024) {
                        double tb = mb / 1024;

                        if (withSuffix) {
                            return decimalFormat.format(tb) + "TB";
                        }

                        return decimalFormat.format(tb);
                    }

                    if (withSuffix) {
                        return decimalFormat.format(gb) + "GB";
                    }
                    return decimalFormat.format(gb);
                }

                if (withSuffix) {
                    return decimalFormat.format(mb) + "MB";
                }
                return decimalFormat.format(mb);
            }

            if (withSuffix) {
                return decimalFormat.format(kb) + "KB";
            }
            return decimalFormat.format(kb);
        } else {
            if (withSuffix) {
                return size + "B";
            }
            return String.valueOf(size);
        }
    }

    /**
     * 格式化时间
     *
     * @param time      毫秒值/秒值
     * @param isSecond  是否为秒值
     * @param isMonth   是否只取月份（年/月/日）
     * @param delimiter 分隔符号
     * @return 返回格式化后的时间
     */
    public static String generateTime(long time, boolean isSecond, boolean isMonth, String delimiter) {
        String format = "yyyy" + delimiter + "MM" + delimiter + "dd HH:mm";
        Date date = new Date(isSecond ? time * 1000 : time);

        if (isMonth) {
            format = "yyyy" + delimiter + "MM" + delimiter + "dd";
        }

        return new SimpleDateFormat(format, Locale.CHINA).format(date);
    }

    /**
     * 格式化时间
     *
     * @param time     毫秒值/秒值
     * @param pattern  时间格式
     * @param isSecond 是否为秒值
     * @return 返回格式化后的时间
     */
    public static String generateTime(long time, String pattern, boolean isSecond) {
        Date date = new Date(isSecond ? time * 1000 : time);
        return new SimpleDateFormat(pattern, Locale.CHINA).format(date);

    }

    /**
     * 解析字符串时间
     *
     * @param strTime 字符串时间
     * @return 毫秒值时间
     */
    public static long formatStrTime(String strTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date date;
        try {
            date = simpleDateFormat.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();

            return 0;
        }

        return date.getTime();
    }

    /**
     * 获取现在是一周的第几天
     */
    public static String getWeekday() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        switch (i) {
            case Calendar.SUNDAY:
                return "星期日";
            case Calendar.MONDAY:
                return "星期一";
            case Calendar.TUESDAY:
                return "星期二";
            case Calendar.WEDNESDAY:
                return "星期三";
            case Calendar.THURSDAY:
                return "星期四";
            case Calendar.FRIDAY:
                return "星期五";
            case Calendar.SATURDAY:
                return "星期六";
            default:
                return "ERROR";
        }
    }

    /**
     * 将编码格式为deflate的响应体进行解码
     *
     * @param bytes 未解码数据
     * @return 解码后的数据
     */
    public static byte[] unZipXML(byte[] bytes) {
        byte[] byteArrayTemp = new byte[bytes.length + 2];
        System.arraycopy(bytes, 0, byteArrayTemp, 2, bytes.length);
        byteArrayTemp[0] = 0x78;
        byteArrayTemp[1] = 0x01;

        bytes = byteArrayTemp;

        Inflater inflater = new Inflater();
        inflater.setInput(bytes);

        byte[] bufferArray = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        try {
            int flag = 1;
            while (flag != 0) {
                flag = inflater.inflate(bufferArray);
                byteArrayOutputStream.write(bufferArray, 0, flag);
            }
            bytes = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        inflater.end();
        return bytes;
    }

    private final static String TABLE = "fZodR9XQDSUm21yCkr6zBqiveYah8bt4xsWpHnJE7jL5VG3guMTKNPAwcF";
    private final static int Xor = 177451812;
    private final static long Add = 100618342136696320L;
    private final static int[] S = { 11, 10, 3, 8, 4, 6, 2, 9, 5, 7 };
    private final static HashMap<Character, Integer> Tr = new HashMap<>((int) (58 / 0.75 + 1));

    static {
        for (int i = 0; i < 58; i++) {
            Tr.put(TABLE.charAt(i), i);
        }
    }

    public static long bv2av(String bv) {
        long r = 0;
        long pow = 1;
        for (int i = 0; i < 10; i++) {
            r += Tr.get(bv.charAt(S[i])) * pow;
            pow *= 58;
        }

        return (r - Add) ^ Xor;
    }

    public static String av2bv(long avNum) {
        long x1 = (avNum ^ Xor) + Add;
        long pow = 1;
        char[] r = "BV          ".toCharArray();
        for (int i = 0; i < 10; i++) {
            int index = (int) (x1 / pow % 58);
            pow *= 58;
            r[S[i]] = TABLE.charAt(index);
        }

        return String.valueOf(r);
    }

    public static Map<String, String> createPlayerVideoHeader(String id, boolean isPgc) {
        HashMap<String, String> header = new HashMap<>(3);
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.81 Safari/537.36 Edg/104.0.1293.54");
        header.put("Connection", "keep-alive");
        header.put("Referer", id.startsWith("BV") ? ("https://www.bilibili.com/video/" + id) : ("https://www.bilibili.com/bangumi/play/ep" + id));

        return header;
    }

    /**
     * @param qn 清晰度代码
     * @return 1：需VIP，2：需登录，3：不需登录
     */
    public static int videoIdentify(int qn) {
        if (qn > 64) {
            if (qn == 74 || qn == 80) {
                return 2;
            } else {
                return 1;
            }
        }

        return 3;
    }
}
