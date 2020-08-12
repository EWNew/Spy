package cn.com.debuggers.spy.Analysis.service.impl;

import cn.com.debuggers.spy.Analysis.entity.IndustryHeavy;
import cn.com.debuggers.spy.Analysis.mapper.IndustryHeavyMapper;
import cn.com.debuggers.spy.Analysis.service.IIndustryHeavyService;
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
public class IndustryHeavyServiceImpl extends ServiceImpl<IndustryHeavyMapper, IndustryHeavy> implements IIndustryHeavyService {

    @Override
    public boolean hasIndustryHeavy(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        if(count(wrapper)==0)return false;
        return true;
    }

    @Override
    public IndustryHeavy getIndustryHeavy(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        IndustryHeavy industryHeavy=getOne(wrapper);
        return industryHeavy;
    }
}
