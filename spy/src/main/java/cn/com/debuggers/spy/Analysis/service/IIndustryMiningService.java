package cn.com.debuggers.spy.Analysis.service;

import cn.com.debuggers.spy.Analysis.entity.IndustryBuilding;
import cn.com.debuggers.spy.Analysis.entity.IndustryMining;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyw
 * @since 2020-07-27
 */
public interface IIndustryMiningService extends IService<IndustryMining> {
    public boolean hasIndustryMining(String creditCode);
    public IndustryMining getIndustryMining(String creditCode);
}
