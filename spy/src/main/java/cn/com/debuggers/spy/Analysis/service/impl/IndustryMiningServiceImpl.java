package cn.com.debuggers.spy.Analysis.service.impl;

import cn.com.debuggers.spy.Analysis.entity.IndustryMining;
import cn.com.debuggers.spy.Analysis.mapper.IndustryMiningMapper;
import cn.com.debuggers.spy.Analysis.service.IIndustryMiningService;
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
public class IndustryMiningServiceImpl extends ServiceImpl<IndustryMiningMapper, IndustryMining> implements IIndustryMiningService {

    @Override
    public boolean hasIndustryMining(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        if(count(wrapper)==0)return false;
        return true;
    }

    @Override
    public IndustryMining getIndustryMining(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        IndustryMining industryMining=getOne(wrapper);
        return industryMining;
    }
}
