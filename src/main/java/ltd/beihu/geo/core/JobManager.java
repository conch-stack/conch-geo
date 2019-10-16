package ltd.beihu.geo.core;

import com.google.common.base.Throwables;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import ltd.beihu.geo.common.Status;
import ltd.beihu.geo.dingtalk.SendTextMessageWarning;
import ltd.beihu.geo.utils.NumberUtils;
import tech.spiro.addrparser.parser.LocationParserEngine;

import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @Author: zjz
 * @Desc: 计算位点
 * @Date: 2019/9/18
 * @Version: V1.0.0
 */
@Slf4j
public class JobManager {

    private ExecutorService manager;
    private LocationParserEngine engine;
    private AreaAvailable areaAvailable;
    private SendTextMessageWarning sendTextMessageWarning;

    public JobManager(ExecutorService manager, LocationParserEngine engine, AreaAvailable areaAvailable, SendTextMessageWarning sendTextMessageWarning) {
        this.manager = manager;
        this.engine = engine;
        this.areaAvailable = areaAvailable;
        this.sendTextMessageWarning = sendTextMessageWarning;
    }

    /**
     * 计算位点
     */
    public void calculate(Job job) {
        log.info("=====: [JobManager] 任务Job: [{}] 开始运行...", job.getCity());
        try {
            Set<Double> allLat = Sets.newHashSet();
            for (int i = 0; i < (job.getRange().getMaxLat() - job.getRange().getMinLat())/job.getD_lat(); i++) {
                allLat.add(NumberUtils.formetDouble(4, job.getRange().getMinLat() + job.getD_lat() * i));
            }
            Set<Double> allLng = Sets.newHashSet();
            for (int i = 0; i < (job.getRange().getMaxLng() - job.getRange().getMinLng())/job.getD_lng(); i++) {
                allLng.add(NumberUtils.formetDouble(4,job.getRange().getMinLng() + job.getD_lng() * i));
            }
            if (job.getEdge()) {
                allLat.add(job.getRange().getMinLat());
                allLat.add(job.getRange().getMaxLat());
                allLng.add(job.getRange().getMinLng());
                allLng.add(job.getRange().getMaxLng());
            }
            CountDownLatch countDownLatch = new CountDownLatch(allLat.size() * allLng.size());
            for (Double lat : allLat) {
                for (Double lng : allLng) {
                    // 异步迭代
                    manager.execute(new JobTask(job.getCity(), countDownLatch, engine, job.getAreaPointDao(), lat, lng));
                }
            }
            countDownLatch.await();
            log.info("=====: [JobManager] 任务Job: [{}] 运行结束", job.getCity());
            // 迭代结束 更新redis 钉钉提示
            areaAvailable.addAvailable(job.getCity(), Status.VALID);
            sendTextMessageWarning.smsLeft(job, "完成!");
        } catch (Exception e) {
            log.error("=====: [JobManager] Job运行异常 [{}]", Throwables.getStackTraceAsString(e));
        }
    }
}
