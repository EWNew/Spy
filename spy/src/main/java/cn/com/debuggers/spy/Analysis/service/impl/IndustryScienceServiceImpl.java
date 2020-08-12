package cn.com.debuggers.spy.Analysis.service.impl;

import cn.com.debuggers.spy.Analysis.entity.IndustryScience;
import cn.com.debuggers.spy.Analysis.mapper.IndustryScienceMapper;
import cn.com.debuggers.spy.Analysis.service.IIndustryScienceService;
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
public class IndustryScienceServiceImpl extends ServiceImpl<IndustryScienceMapper, IndustryScience> implements IIndustryScienceService {

    @Override
    public boolean hasIndustryScience(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        if(count(wrapper)==0)return false;
        return true;
    }

    @Override
    public IndustryScience getIndustryScience(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        IndustryScience industryScience =getOne(wrapper);
        return industryScience;
    }
}
