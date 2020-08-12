package cn.com.debuggers.spy.Analysis.service.impl;

import cn.com.debuggers.spy.Analysis.entity.IndustryLight;
import cn.com.debuggers.spy.Analysis.mapper.IndustryLightMapper;
import cn.com.debuggers.spy.Analysis.service.IIndustryLightService;
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
public class IndustryLightServiceImpl extends ServiceImpl<IndustryLightMapper, IndustryLight> implements IIndustryLightService {

    @Override
    public boolean hasIndustryLight(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        if(count(wrapper)==0)return false;
        return true;
    }

    @Override
    public IndustryLight getIndustryLight(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        IndustryLight industryLight=getOne(wrapper);
        return industryLight;
    }
}
