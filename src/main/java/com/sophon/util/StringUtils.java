package com.sophon.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 * @author tiansheng
 * @date 2019/8/23 23:52
 * @version 1.0.0
 * @since 1.8
 */
public class StringUtils {

    /**
     * 字符串是否为空
     *
     * @param s 目标字符串
     * @return 返回boolean
     */
    public static boolean isEmpty(String s) {
        if (s == null) {
            return true;
        }
        // Unicode 编码下的空格
        if ("\u0000".equals(s)) {
            return true;
        }
        return s.length() == 0 || "".equals(s);
    }

    /**
     * 是否存在某个字符串
     *
     * @param s     字符串
     * @param regex 需要查找的字符串(支持正则)
     * @return 返回boolean
     */
    public static boolean isExist(String s, String regex) {
        if (s.contains(regex)) return true;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }

    /**
     * 获取最后一个字符
     *
     * @param v 目标字符串
     * @return 返回该字符串的最后一个字符
     */
    public static String getLastString(String v) {
        return v.substring(v.length() - 1);
    }

    /**
     * 删除最后一个字符
     *
     * @param v 目标字符串
     * @return 返回处理后的字符串
     */
    public static String removeLastString(String v) {
        return v.substring(0, v.length() - 1);
    }

    /**
     * 判断当前字符串是不是数字
     *
     * @param v 目标字符串
     * @return 返回boolean
     */
    public static boolean isNumber(String v) {
        return v.matches("^[0-9]*$");
    }

    /**
     * 删除所有非数字的字符
     *
     * @param v 目标字符串
     * @return 返回处理后的字符串
     */
    public static String removeNotNumber(String v) {
        return v.replaceAll("[^\\d]", "");
    }

    /**
     * 获取一个字符串的开始位置和结束位置
     *
     * @param v    源字符串
     * @param find 需要查找的字符串
     * @return 数组 0=开始位置 1=结束位置
     */
    public static int[] getStringStartAndEndIndex(String v, String find) {
        int start = -1; // 开始下标
        int end = -1; // 结束下标
        int currentIndex = 0; // 当前下标 -1等于当前源字符串长度已遍历完
        char[] source = v.toCharArray(); // 源字符串char数组
        char[] target = find.toCharArray();
        while (source.length >= currentIndex) {
            boolean result = true;
            for (int i = 0; i < target.length; i++) {
                if (source[currentIndex] == target[i]) {
                    result = true;
                }else{
                    result = false;
                    currentIndex++;
                    break;
                }
                currentIndex++;
            }
            if (result) {
                start = currentIndex - target.length;
                end = currentIndex;
                break;
            }
        }
        return new int[]{start, end};
    }

}
