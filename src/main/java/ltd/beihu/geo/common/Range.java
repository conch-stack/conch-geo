package ltd.beihu.geo.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zjz
 * @Desc: 范围
 * @Date: 2019/9/18
 * @Version: V1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Range {
    /**
     * 最小纬度
     */
    private double minLat;
    /**
     * 最大纬度
     */
    private double maxLat;
    /**
     * 最小经度
     */
    private double minLng;
    /**
     * 最大经度
     */
    private double maxLng;

}
