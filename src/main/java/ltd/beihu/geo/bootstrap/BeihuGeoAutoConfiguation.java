package ltd.beihu.geo.bootstrap;

import ltd.beihu.geo.core.AreaAvailable;
import ltd.beihu.geo.core.JobManager;
import ltd.beihu.geo.dingtalk.SendTextMessageWarning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import tech.spiro.addrparser.io.RegionDataInput;
import tech.spiro.addrparser.io.file.JSONFileRegionDataInput;
import tech.spiro.addrparser.parser.LocationParserEngine;
import tech.spiro.addrparser.parser.ParserEngineException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zjz
 * @Desc:
 * @Date: 2019/3/21
 * @Version: V1.0.0
 */
@Configuration
@EnableConfigurationProperties(BeihuGeoJobProperties.class)
public class BeihuGeoAutoConfiguation {
    private final static Logger LOG = LoggerFactory.getLogger(BeihuGeoAutoConfiguation.class);

    @Bean
    @ConditionalOnMissingBean
    public LocationParserEngine engine() throws ParserEngineException, IOException {
        Resource resource = new ClassPathResource("china-region.json");
        InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
        // china-region.json文件作为基础数据
        RegionDataInput regionDataInput = new JSONFileRegionDataInput(inputStreamReader);
        // 创建并初始化位置解析引擎，一般配置为全局单例
        LocationParserEngine engine = new LocationParserEngine(regionDataInput);
        // 初始化，加载数据，比较耗时
        LOG.info("====:> 初始化加载位置解析引擎...");
        engine.init();
        LOG.info("====:> 初始化加载位置解析引擎 完成！");
        return engine;
    }

    @Bean
    @ConditionalOnMissingBean
    public AreaAvailable areaAvailable() {
        return new AreaAvailable();
    }

    @Bean
    @ConditionalOnMissingBean
    public SendTextMessageWarning sendTextMessageWarning() {
        return new SendTextMessageWarning();
    }

    @Bean
    @ConditionalOnMissingBean
    public JobManager jobManager(BeihuGeoJobProperties beihuGeoJobProperties, LocationParserEngine engine,
                                 AreaAvailable areaAvailable, SendTextMessageWarning sendTextMessageWarning) {
        return new JobManager(
                new ThreadPoolExecutor(beihuGeoJobProperties.getManager().getCorePoolSize(),
                        beihuGeoJobProperties.getManager().getMaximumPoolSize(),
                        beihuGeoJobProperties.getManager().getKeepAliveTime(), TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>()),
                engine,
                areaAvailable,
                sendTextMessageWarning);
    }

}
