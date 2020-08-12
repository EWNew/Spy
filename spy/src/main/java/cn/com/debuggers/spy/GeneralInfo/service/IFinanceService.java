package cn.com.debuggers.spy.GeneralInfo.service;

import cn.com.debuggers.spy.GeneralInfo.entity.Finance;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyw
 * @since 2020-07-21
 */
public interface IFinanceService extends IService<Finance> {

    public Finance getFinance(String creditCode);
}
