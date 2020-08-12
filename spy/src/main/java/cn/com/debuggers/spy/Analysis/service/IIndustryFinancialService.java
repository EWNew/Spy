package cn.com.debuggers.spy.Analysis.service;

import cn.com.debuggers.spy.Analysis.entity.IndustryBuilding;
import cn.com.debuggers.spy.Analysis.entity.IndustryFinancial;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyw
 * @since 2020-07-27
 */
public interface IIndustryFinancialService extends IService<IndustryFinancial> {
    public boolean hasIndustryFinancial(String creditCode);
    public IndustryFinancial getIndustryFinancial(String creditCode);
}
