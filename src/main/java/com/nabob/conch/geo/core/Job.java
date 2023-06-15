package com.nabob.conch.geo.core;

import com.nabob.conch.geo.common.Range;
import com.nabob.conch.geo.dao.AreaPointDao;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Job {

    /**
     * 城市
     */
    @NotBlank(message = "请选择城市")
    private String city;
    /**
     * 纬度区间
     */
    @NotNull(message = "请填写纬度区间")
    private Double d_lat;
    /**
     * 经度区间
     */
    @NotNull(message = "请填写经度区间")
    private Double d_lng;
    /**
     * 是否包含范围
     */
    @NotNull(message = "请选择是否包含范围")
    private Boolean edge;
    /**
     * 是否需要重置
     */
    @NotNull(message = "请选择是否需要重置")
    private Boolean reset;

    /**
     * 范围
     */
    private Range range;
    /**
     * 数据源
     */
    private AreaPointDao areaPointDao;
}
