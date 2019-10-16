package ltd.beihu.geo.rest;

import ltd.beihu.core.tools.code.BasicServiceCode;
import ltd.beihu.core.web.boot.response.BasicResponse;
import ltd.beihu.core.web.boot.response.JsonResponse;
import ltd.beihu.core.web.boot.utils.Asserts;
import ltd.beihu.geo.code.BeihuGeoCode;
import ltd.beihu.geo.core.AreaAvailable;
import ltd.beihu.geo.core.Job;
import ltd.beihu.geo.core.JobFactory;
import ltd.beihu.geo.core.JobManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        return BasicResponse.success(BeihuGeoCode.SUCCESS);
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
