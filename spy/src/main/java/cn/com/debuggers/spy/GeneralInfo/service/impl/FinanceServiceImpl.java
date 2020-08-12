package cn.com.debuggers.spy.GeneralInfo.service.impl;

import cn.com.debuggers.spy.GeneralInfo.entity.Finance;
import cn.com.debuggers.spy.GeneralInfo.mapper.FinanceMapper;
import cn.com.debuggers.spy.GeneralInfo.service.IFinanceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nyw
 * @since 2020-07-21
 */
@Service
public class FinanceServiceImpl extends ServiceImpl<FinanceMapper, Finance> implements IFinanceService {

    @Override
    public Finance getFinance(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        Finance finance=getOne(wrapper);
        return finance;
    }
}
