package cn.com.debuggers.spy.Analysis.service.impl;

import cn.com.debuggers.spy.Analysis.entity.IndustryPublic;
import cn.com.debuggers.spy.Analysis.mapper.IndustryPublicMapper;
import cn.com.debuggers.spy.Analysis.service.IIndustryPublicService;
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
public class IndustryPublicServiceImpl extends ServiceImpl<IndustryPublicMapper, IndustryPublic> implements IIndustryPublicService {

    @Override
    public boolean hasIndustryPublic(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        if(count(wrapper)==0)return false;
        return true;
    }

    @Override
    public IndustryPublic getIndustryPublic(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        IndustryPublic industryPublic=getOne(wrapper);
        return industryPublic;
    }
}
