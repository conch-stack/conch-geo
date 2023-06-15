package com.nabob.conch.geo.utils;

import java.math.BigDecimal;

/**
 * @Author: zjz
 * @Desc:
 * @Date: 18-7-18
 * @Version: V1.0.0
 */
public class NumberUtils {

    /**
     * 获取不带 百分号 的 Double 数据 保证精度
     */
    public static Double formetDouble$1(Double value) {
        return doFormetDouble(2, BigDecimal.valueOf(value)).doubleValue();
    }

    /**
     * 获取带 百分号 的 Double 数据 保证精度
     */
    public static String formetDouble(Double value) {
        return doFormetDouble(2, BigDecimal.valueOf(value).multiply(BigDecimal.valueOf(100))).doubleValue() + "%";
    }

    /**
     * 转换 Double
     * @param newScale 保留位数
     * @param value 待转数据
     * @return 四舍五入 BigDecimal
     */
    public static Double formetDouble(int newScale, Double value) {
        return doFormetDouble(newScale, BigDecimal.valueOf(value)).doubleValue();
    }

    /**
     * 转换 Double
     * @param newScale 保留位数
     * @param value 待转数据
     * @return 四舍五入 BigDecimal
     */
    public static BigDecimal doFormetDouble(int newScale, BigDecimal value) {
        return value.setScale(newScale, BigDecimal.ROUND_HALF_UP);
    }

}
