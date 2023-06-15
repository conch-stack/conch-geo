package com.nabob.conch.geo.modle.dsl;

import com.gitee.hengboy.mybatis.enhance.dsl.expression.ColumnExpression;
import com.gitee.hengboy.mybatis.enhance.dsl.expression.TableExpression;
import com.nabob.conch.geo.modle.AreaPoint;

/**
 * 城市位点
 * @author ApiBoot Mybatis Enhance Codegen
 */
public class DAreaPoint extends TableExpression<AreaPoint> {

    public DAreaPoint(String root) {
        super(root);
    }

    public static DAreaPoint DSL() {
        return new DAreaPoint("area_point");
    }

    /**
     * 主键ID
     */
    public ColumnExpression id = new ColumnExpression("id", this);
    /**
     * 省
     */
    public ColumnExpression prov = new ColumnExpression("prov", this);
    /**
     * 市
     */
    public ColumnExpression city = new ColumnExpression("city", this);
    /**
     * 区
     */
    public ColumnExpression district = new ColumnExpression("district", this);
    /**
     * 纬度
     */
    public ColumnExpression lat = new ColumnExpression("lat", this);
    /**
     * 经度
     */
    public ColumnExpression lng = new ColumnExpression("lng", this);
    /**
     * 创建时间
     */
    public ColumnExpression ct = new ColumnExpression("ct", this);
    @Override
    public ColumnExpression[] getColumns() {
        return new ColumnExpression[]{id, prov, city, district, lat, lng, ct};
    }

}

