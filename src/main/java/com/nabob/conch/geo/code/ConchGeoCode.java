package com.nabob.conch.geo.code;

import com.nabob.conch.tools.code.ServiceCode;

import java.util.stream.Stream;

/**
 * @Author: zjz
 * @Desc:
 * @Date: 2019/1/7
 * @Version: V1.0.0
 */
public enum ConchGeoCode implements ServiceCode {

    SUCCESS(10000, "SUCCESS", "请求成功"),
    ERROR(10001, "SYSTEM_EXCEPTION", "系统异常"),
    FAILED(10002, "FAILED", "请求失败"),

    AREA_POINT_EXIST(20000, "AREA_POINT_EXIST", "城市位点信息已存在"),
    AREA_POINT_RUNNING(20001, "AREA_POINT_RUNNING", "城市位点信息正在计算"),
    AREA_POINT_NOT_EXIST(20002, "AREA_POINT_NOT_EXIST", "城市位点信息不存在")
    ;

    ConchGeoCode(int code, String desc) {
        this(code, desc, "请求成功");
    }

    ConchGeoCode(int code, String desc, String message) {
        this.code = code;
        this.message = message;
        this.desc = desc;
    }

    private int code;
    private String desc;
    private String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String getMesg() {
        return message;
    }

    @Override
    public void setMesg(String mesg) {
        this.message = message;
    }

    public static ConchGeoCode parse(int code) {
        return Stream.of(values())
                .filter(baseCode -> baseCode.getCode() == code)
                .findFirst()
                .orElse(ERROR);
    }
    public static ConchGeoCode findMessage(String message) {
        return Stream.of(values())
                .filter(baseCode -> baseCode.getMesg().equals(message))
                .findFirst()
                .orElse(ERROR);
    }
}

