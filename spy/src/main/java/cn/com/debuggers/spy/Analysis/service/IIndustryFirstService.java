package cn.com.debuggers.spy.Analysis.service;

import cn.com.debuggers.spy.Analysis.entity.IndustryBuilding;
import cn.com.debuggers.spy.Analysis.entity.IndustryFirst;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyw
 * @since 2020-07-27
 */
public interface IIndustryFirstService extends IService<IndustryFirst> {
    public boolean hasIndustryFirst(String creditCode);
    public IndustryFirst getIndustryFirst(String creditCode);
}
