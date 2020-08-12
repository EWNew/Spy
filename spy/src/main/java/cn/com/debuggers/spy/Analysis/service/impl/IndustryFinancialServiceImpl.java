package cn.com.debuggers.spy.Analysis.service.impl;

import cn.com.debuggers.spy.Analysis.entity.IndustryFinancial;
import cn.com.debuggers.spy.Analysis.mapper.IndustryFinancialMapper;
import cn.com.debuggers.spy.Analysis.service.IIndustryFinancialService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nyw
 * @since 2020-07-27
 */
@Service
public class IndustryFinancialServiceImpl extends ServiceImpl<IndustryFinancialMapper, IndustryFinancial> implements IIndustryFinancialService {

    @Override
    public boolean hasIndustryFinancial(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        if(count(wrapper)==0)return false;
        return true;
    }

    @Override
    public IndustryFinancial getIndustryFinancial(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        IndustryFinancial industryFinancial=getOne(wrapper);
        return industryFinancial;
    }
}
