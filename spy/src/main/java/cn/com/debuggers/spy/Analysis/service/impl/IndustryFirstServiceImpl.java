package cn.com.debuggers.spy.Analysis.service.impl;

import cn.com.debuggers.spy.Analysis.entity.IndustryFirst;
import cn.com.debuggers.spy.Analysis.mapper.IndustryFirstMapper;
import cn.com.debuggers.spy.Analysis.service.IIndustryFirstService;
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
public class IndustryFirstServiceImpl extends ServiceImpl<IndustryFirstMapper, IndustryFirst> implements IIndustryFirstService {

    @Override
    public boolean hasIndustryFirst(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        if(count(wrapper)==0)return false;
        return true;
    }

    @Override
    public IndustryFirst getIndustryFirst(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        IndustryFirst industryFirst=getOne(wrapper);
        return industryFirst;
    }
}
