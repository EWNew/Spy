package cn.com.debuggers.spy.Analysis.service;

import cn.com.debuggers.spy.Analysis.entity.IndustryBuilding;
import cn.com.debuggers.spy.Analysis.entity.IndustryHeavy;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyw
 * @since 2020-07-27
 */
public interface IIndustryHeavyService extends IService<IndustryHeavy> {
    public boolean hasIndustryHeavy(String creditCode);
    public IndustryHeavy getIndustryHeavy(String creditCode);
}
