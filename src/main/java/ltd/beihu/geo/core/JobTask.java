package ltd.beihu.geo.core;

import com.google.common.base.Throwables;
import ltd.beihu.core.tools.utils.IDUtils;
import ltd.beihu.geo.dao.AreaPointDao;
import ltd.beihu.geo.modle.AreaPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.spiro.addrparser.common.RegionInfo;
import tech.spiro.addrparser.parser.Location;
import tech.spiro.addrparser.parser.LocationParserEngine;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: zjz
 * @Desc:
 * @Date: 2019/3/22
 * @Version: V1.0.0
 */
public class JobTask implements Runnable{

    private final static Logger LOG = LoggerFactory.getLogger(JobTask.class);
    private CountDownLatch countDownLatch;
    private LocationParserEngine engine;
    private AreaPointDao areaPointDao;
    /**
     * 城市
     */
    private String city;
    /**
     * 纬度
     */
    private double lat;
    /**
     * 经度
     */
    private double lng;

    public JobTask(String city, CountDownLatch countDownLatch, LocationParserEngine engine, AreaPointDao areaPointDao, double lat, double lng) {
        this.city = city;
        this.countDownLatch = countDownLatch;
        this.engine = engine;
        this.areaPointDao = areaPointDao;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public void run() {
        try {
            LOG.info("====: [JobTask] 接收到JobTask：[{}],[{}]", lat, lng);
            // 执行解析操作
            Location location = engine.parse(lng,lat);
            if (null == location) {
                LOG.error("=====: [JobTask] Location解析异常，未解析到数据：[{}],[{}]", lat, lng);
                return;
            }
            // 获取省市区信息
            RegionInfo provInfo = location.getProv();
            RegionInfo cityInfo = location.getCity();
            RegionInfo districtInfo = location.getDistrict();
            if (null == provInfo || null == cityInfo || null == districtInfo) {
                LOG.error("=====: [JobTask] RegionInfo解析异常，未解析到数据：[{}],[{}]", lat, lng);
                return;
            }
            if (city.equals(provInfo.getName())) {
                AreaPoint areaPoint = new AreaPoint();
                areaPoint.setId(IDUtils.UUID());
                areaPoint.setProv(provInfo.getName());
                areaPoint.setCity(cityInfo.getName());
                areaPoint.setDistrict(districtInfo.getName());
                areaPoint.setLat(lat);
                areaPoint.setLng(lng);
                areaPointDao.insert(areaPoint);
                LOG.info("====: [JobTask] JobTask：[{}],[{}], 运行结束", lat, lng);
            } else {
                LOG.warn("====: [JobTask] JobTask：[{}],[{}], 城市不匹配", lat, lng);
            }
        } catch (Exception e) {
            LOG.error("=====: [JobTask] JobTask运行异常 [{}]", Throwables.getStackTraceAsString(e));
        } finally {
            countDownLatch.countDown();
        }
    }
}
