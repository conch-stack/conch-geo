package ltd.beihu.geo.core;

import ltd.beihu.core.tools.code.BasicServiceCode;
import ltd.beihu.core.web.boot.utils.Asserts;
import ltd.beihu.geo.code.BeihuGeoCode;
import ltd.beihu.geo.common.ChinaAreas;
import ltd.beihu.geo.common.Range;
import ltd.beihu.geo.common.Status;
import ltd.beihu.geo.dao.AreaPointDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: zjz
 * @Desc:
 * @Date: 2019/9/18
 * @Version: V1.0.0
 */
@Component
public class JobFactory {

    @Resource
    private AreaAvailable areaAvailable;
    @Resource
    private AreaPointDao areaPointDao;

    public Job createJob(Job job){
        Asserts.notNull(job, BasicServiceCode.BAD_REQUEST);
        Asserts.isTrue(ChinaAreas.chinaAreas.containsKey(job.getCity()), BasicServiceCode.BAD_REQUEST);

        if (job.getReset()) {
            // 清除redis
            areaAvailable.removeAvailable(job.getCity());
            // 删除数据库
            areaPointDao.removeByProv(job.getCity());
        } else {
            // check redis
            String available = areaAvailable.getAvailable(job.getCity());
            Asserts.isFalse(available.equals(Status.DEFAULT), BeihuGeoCode.AREA_POINT_RUNNING);
            Asserts.isFalse(available.equals(Status.VALID), BeihuGeoCode.AREA_POINT_EXIST);
        }
        Range range = ChinaAreas.chinaAreas.get(job.getCity());
        job.setRange(range);
        job.setAreaPointDao(areaPointDao);
        areaAvailable.addAvailable(job.getCity(), Status.DEFAULT);
        return job;
    }
}
