package com.nabob.conch.geo.core;

import com.nabob.conch.geo.code.ConchGeoCode;
import com.nabob.conch.geo.common.ChinaAreas;
import com.nabob.conch.geo.common.Range;
import com.nabob.conch.geo.common.Status;
import com.nabob.conch.geo.dao.AreaPointDao;
import com.nabob.conch.tools.code.BasicServiceCode;
import com.nabob.conch.web.utils.Asserts;
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
            Asserts.isFalse(available.equals(Status.DEFAULT), ConchGeoCode.AREA_POINT_RUNNING);
            Asserts.isFalse(available.equals(Status.VALID), ConchGeoCode.AREA_POINT_EXIST);
        }
        Range range = ChinaAreas.chinaAreas.get(job.getCity());
        job.setRange(range);
        job.setAreaPointDao(areaPointDao);
        areaAvailable.addAvailable(job.getCity(), Status.DEFAULT);
        return job;
    }
}
