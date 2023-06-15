package com.nabob.conch.geo.rest;

import com.gitee.hengboy.mybatis.pageable.Page;
import com.nabob.conch.geo.code.ConchGeoCode;
import com.nabob.conch.geo.common.Status;
import com.nabob.conch.geo.core.AreaAvailable;
import com.nabob.conch.geo.dao.AreaPointDao;
import com.nabob.conch.geo.dto.AreaPointPageDto;
import com.nabob.conch.geo.modle.AreaPoint;
import com.nabob.conch.tools.code.BasicServiceCode;
import com.nabob.conch.web.response.BasicResponse;
import com.nabob.conch.web.response.JsonResponse;
import com.nabob.conch.web.utils.Asserts;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        Asserts.isTrue(available.equals(Status.VALID), ConchGeoCode.AREA_POINT_NOT_EXIST);
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
        Asserts.isTrue(available.equals(Status.VALID), ConchGeoCode.AREA_POINT_NOT_EXIST);
        return BasicResponse.success(areaPointDao.findByProv(prov));
    }

}
