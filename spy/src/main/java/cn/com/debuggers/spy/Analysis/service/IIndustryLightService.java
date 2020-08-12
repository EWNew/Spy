package cn.com.debuggers.spy.Analysis.service;

import cn.com.debuggers.spy.Analysis.entity.IndustryBuilding;
import cn.com.debuggers.spy.Analysis.entity.IndustryLight;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyw
 * @since 2020-07-27
 */
public interface IIndustryLightService extends IService<IndustryLight> {
    public boolean hasIndustryLight(String creditCode);
    public IndustryLight getIndustryLight(String creditCode);
}
