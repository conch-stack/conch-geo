package ltd.beihu.geo.dao;

import com.gitee.hengboy.mybatis.enhance.dsl.factory.EnhanceDslFactory;
import com.gitee.hengboy.mybatis.pageable.Page;
import com.gitee.hengboy.mybatis.pageable.request.PageableRequest;
import ltd.beihu.geo.mapper.AreaPointMapper;
import ltd.beihu.geo.modle.AreaPoint;
import ltd.beihu.geo.modle.dsl.DAreaPoint;
import ltd.beihu.geo.dto.AreaPointPageDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: zjz
 * @Desc:
 * @Date: 2019/8/5
 * @Version: V1.0.0
 */
@Component
public class AreaPointDao {

    /**
     * ApiBoot Enhance 动态工厂
     */
    @Resource
    private EnhanceDslFactory dslFactory;
    @Resource
    private AreaPointMapper areaPointMapper;
    private static DAreaPoint dAreaPoint = DAreaPoint.DSL();

    /**
     * 新增
     */
    public void insert(AreaPoint areaPoint) {
        areaPointMapper.insert(areaPoint);
    }

    /**
     * 删除
     */
    public void removeByProv(String prov) {
        areaPointMapper.removeByProv(prov);
    }

    /**
     * 不分页
     */
    public List<AreaPoint> findByProv(String prov) {
        return areaPointMapper.findByProv(prov);
    }

    /**
     * 分页
     */
    public Page<AreaPoint> pageAreaPoint(AreaPointPageDto areaPointPageDto) {
        return PageableRequest.of(areaPointPageDto).request(() -> findLike(areaPointPageDto));
    }

    private List<AreaPoint> findLike(AreaPointPageDto areaPointPageDto) {
        return dslFactory.createSearchable()
                .selectFrom(dAreaPoint) // select *
                .where(dAreaPoint.prov.eq(areaPointPageDto.getProv()))
                .resultType(AreaPoint.class)
                .fetch();
    }
}
