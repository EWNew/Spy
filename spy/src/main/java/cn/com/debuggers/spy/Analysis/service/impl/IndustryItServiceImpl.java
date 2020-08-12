package cn.com.debuggers.spy.Analysis.service.impl;

import cn.com.debuggers.spy.Analysis.entity.IndustryIt;
import cn.com.debuggers.spy.Analysis.mapper.IndustryItMapper;
import cn.com.debuggers.spy.Analysis.service.IIndustryItService;
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
public class IndustryItServiceImpl extends ServiceImpl<IndustryItMapper, IndustryIt> implements IIndustryItService {

    @Override
    public boolean hasIndustryIt(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        if(count(wrapper)==0)return false;
        return true;
    }

    @Override
    public IndustryIt getIndustryIt(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        IndustryIt industryIt=getOne(wrapper);
        return industryIt;
    }
}
