package com.nabob.conch.geo.core;

import com.nabob.conch.geo.common.Status;
import com.nabob.conch.redis.RedisGenericUtil;
import com.nabob.conch.tools.code.BasicServiceCode;
import com.nabob.conch.web.utils.Asserts;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: zjz
 * @Desc: 地区是否可用
 * @Date: 2019/9/18
 * @Version: V1.0.0
 */
public class AreaAvailable {

    private static final String REDIS_KEY_PREFIX_AREA = "area";
    @Resource
    private RedisGenericUtil redisGenericUtil;

    /**
     * 设置
     */
    public boolean addAvailable(String city, String status) {
        try {
            Asserts.hasText(city, BasicServiceCode.BAD_REQUEST);
            Asserts.isTrue(Status.contains(status), BasicServiceCode.BAD_REQUEST);
            redisGenericUtil.setHMap(REDIS_KEY_PREFIX_AREA, city, status);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取
     */
    public String getAvailable(String city) {
        Asserts.hasText(city, BasicServiceCode.BAD_REQUEST);
        Optional<String> hMap = redisGenericUtil.getHMap(REDIS_KEY_PREFIX_AREA, city);
        return hMap.orElse("-1");
    }

    /**
     * 去除
     */
    public boolean removeAvailable(String city) {
        try {
            Asserts.hasText(city, BasicServiceCode.BAD_REQUEST);
            redisGenericUtil.deleteHMap(REDIS_KEY_PREFIX_AREA, city);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取所有
     */
    public Map<String, String> getAllAvailable(){
        return redisGenericUtil.entries(REDIS_KEY_PREFIX_AREA, String.class);
    }
}
