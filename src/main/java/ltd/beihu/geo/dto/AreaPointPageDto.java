package ltd.beihu.geo.dto;

import lombok.Data;

/**
 * @Author: zjz
 * @Desc:
 * @Date: 2019/8/6
 * @Version: V1.0.0
 */
@Data
public class AreaPointPageDto extends PageParam{

    /**
     * 地区(例：上海市)
     */
    private String prov;
}
