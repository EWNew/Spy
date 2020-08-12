package cn.com.debuggers.spy.Analysis.service;

import cn.com.debuggers.spy.Analysis.entity.IndustryBuilding;
import cn.com.debuggers.spy.Analysis.entity.IndustryTransport;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyw
 * @since 2020-07-27
 */
public interface IIndustryTransportService extends IService<IndustryTransport> {
    public boolean hasIndustryTransport(String creditCode);
    public IndustryTransport getIndustryTransport(String creditCode);
}
