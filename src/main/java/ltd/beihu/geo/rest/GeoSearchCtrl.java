package ltd.beihu.geo.rest;

import com.gitee.hengboy.mybatis.pageable.Page;
import ltd.beihu.core.tools.code.BasicServiceCode;
import ltd.beihu.core.web.boot.response.BasicResponse;
import ltd.beihu.core.web.boot.response.JsonResponse;
import ltd.beihu.core.web.boot.utils.Asserts;
import ltd.beihu.geo.code.BeihuGeoCode;
import ltd.beihu.geo.common.Status;
import ltd.beihu.geo.core.AreaAvailable;
import ltd.beihu.geo.dao.AreaPointDao;
import ltd.beihu.geo.dto.AreaPointPageDto;
import ltd.beihu.geo.modle.AreaPoint;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 城市位点查询服务
 */
@RestController
@RequestMapping("/search")
public class GeoSearchCtrl {

    @Resource
    private AreaAvailable areaAvailable;
    @Resource
    private AreaPointDao areaPointDao;

    /**
     * 分页查询
     */
    @PostMapping("/pap")
    public BasicResponse<Page<AreaPoint>> pageAreaPoint(@Validated @RequestBody AreaPointPageDto areaPointPageDto) {
        String available = areaAvailable.getAvailable(areaPointPageDto.getProv());
        Asserts.isTrue(available.equals(Status.VALID), BeihuGeoCode.AREA_POINT_NOT_EXIST);
        return BasicResponse.success(areaPointDao.pageAreaPoint(areaPointPageDto));
    }

    /**
     * [不]分页查询
     * @param prov 城市名称
     */
    @GetMapping("/gbp")
    public JsonResponse getAllAvailable(@RequestParam(name = "prov", required = false) String prov) {
        Asserts.hasText(prov, BasicServiceCode.BAD_REQUEST);
        String available = areaAvailable.getAvailable(prov);
        Asserts.isTrue(available.equals(Status.VALID), BeihuGeoCode.AREA_POINT_NOT_EXIST);
        return BasicResponse.success(areaPointDao.findByProv(prov));
    }

}
