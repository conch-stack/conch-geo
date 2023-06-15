package com.nabob.conch.geo.common;

/**
 * @Author: zjz
 * @Desc:
 * @Date: 2019/9/18
 * @Version: V1.0.0
 */
public class Status {

    // 可用
    public static final String VALID = "1";

    // 不可用
    public static final String DEFAULT = "0";

    public static boolean contains(String target) {
        return VALID.equals(target) || DEFAULT.equals(target);
    }

}
