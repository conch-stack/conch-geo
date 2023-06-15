package com.nabob.conch.geo.rest;

import com.nabob.conch.geo.code.ConchGeoCode;
import com.nabob.conch.geo.core.AreaAvailable;
import com.nabob.conch.geo.core.Job;
import com.nabob.conch.geo.core.JobFactory;
import com.nabob.conch.geo.core.JobManager;
import com.nabob.conch.tools.code.BasicServiceCode;
import com.nabob.conch.web.response.BasicResponse;
import com.nabob.conch.web.response.JsonResponse;
import com.nabob.conch.web.utils.Asserts;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 城市位点计算服务
 */
@RestController
@RequestMapping("/job")
public class GeoJobCtrl {

    @Resource
    private JobManager jobManager;
    @Resource
    private JobFactory jobFactory;
    @Resource
    private AreaAvailable areaAvailable;

    /**
     * 提交任务
     */
    @RequestMapping("/trigger")
    public JsonResponse trigger(@RequestBody @Validated Job job) {
        jobManager.calculate(jobFactory.createJob(job));
        return BasicResponse.success(ConchGeoCode.SUCCESS);
    }

    /**
     * 获取所有城市信息
     */
    @GetMapping("/gaa")
    public JsonResponse getAllAvailable() {
        Map<String, String> allAvailable = areaAvailable.getAllAvailable();
        return BasicResponse.success(allAvailable);
    }

    /**
     * 校验城市是否支持
     * @param city 城市名称
     * @return 1 已支持， 0 正在计算中， -1 暂无
     */
    @GetMapping("/check")
    public JsonResponse checkSupport(@RequestParam(name = "city", required = false) String city) {
        Asserts.hasText(city, BasicServiceCode.BAD_REQUEST);
        return BasicResponse.success(areaAvailable.getAvailable(city));
    }
}
