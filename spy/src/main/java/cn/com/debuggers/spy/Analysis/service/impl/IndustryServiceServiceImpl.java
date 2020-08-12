package cn.com.debuggers.spy.Analysis.service.impl;

import cn.com.debuggers.spy.Analysis.entity.IndustryService;
import cn.com.debuggers.spy.Analysis.mapper.IndustryServiceMapper;
import cn.com.debuggers.spy.Analysis.service.IIndustryServiceService;
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
public class IndustryServiceServiceImpl extends ServiceImpl<IndustryServiceMapper, IndustryService> implements IIndustryServiceService {

    @Override
    public boolean hasIndustryService(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        if(count(wrapper)==0)return false;
        return true;
    }

    @Override
    public IndustryService getIndustryService(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        IndustryService industryService=getOne(wrapper);
        return industryService;
    }
}
