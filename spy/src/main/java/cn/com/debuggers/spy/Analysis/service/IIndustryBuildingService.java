package cn.com.debuggers.spy.Analysis.service;

import cn.com.debuggers.spy.Analysis.entity.IndustryBuilding;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyw
 * @since 2020-07-27
 */
public interface IIndustryBuildingService extends IService<IndustryBuilding> {
public boolean hasIndustryBuilding(String creditCode);
public IndustryBuilding getIndustryBuilding(String creditCode);
}
