package com.nabob.conch.geo.modle;

import com.gitee.hengboy.mybatis.enhance.common.annotation.Column;
import com.gitee.hengboy.mybatis.enhance.common.annotation.Id;
import com.gitee.hengboy.mybatis.enhance.common.annotation.Table;
import com.gitee.hengboy.mybatis.enhance.common.enums.KeyGeneratorTypeEnum;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 城市位点
 * @author ApiBoot Mybatis Enhance Codegen
 */
@Data
@Table(name = "area_point")
public class AreaPoint implements java.io.Serializable {

    /**
     * 主键ID
     */
    @Id(generatorType = KeyGeneratorTypeEnum.DIY)
    @Column(name = "id")
    private String id;
    /**
     * 省
     */
    @Column(name = "prov")
    private String prov;
    /**
     * 市
     */
    @Column(name = "city")
    private String city;
    /**
     * 区
     */
    @Column(name = "district")
    private String district;
    /**
     * 纬度
     */
    @Column(name = "lat")
    private Double lat;
    /**
     * 经度
     */
    @Column(name = "lng")
    private Double lng;
    /**
     * 创建时间
     */
    @Column(name = "ct",insertable = false)
    private Timestamp ct;
}

