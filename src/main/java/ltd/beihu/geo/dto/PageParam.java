package ltd.beihu.geo.dto;

import com.gitee.hengboy.mybatis.pageable.common.annotations.PageIndex;
import com.gitee.hengboy.mybatis.pageable.common.annotations.PageSize;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PageParam {

  /**
   * 当前页
   */
  @NotNull(message = "请填写当前页")
  @PageIndex
  private int pageIndex;

  /**
   * 每页个数
   */
  @NotNull(message = "请填写每页数量")
  @PageSize
  private int pageSize;
}