package cn.com.debuggers.spy.Analysis.service.impl;

import cn.com.debuggers.spy.Analysis.entity.IndustryEnergy;
import cn.com.debuggers.spy.Analysis.mapper.IndustryEnergyMapper;
import cn.com.debuggers.spy.Analysis.service.IIndustryEnergyService;
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
public class IndustryEnergyServiceImpl extends ServiceImpl<IndustryEnergyMapper, IndustryEnergy> implements IIndustryEnergyService {

    @Override
    public boolean hasIndustryEnergy(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        if(count(wrapper)==0)return false;
        return true;
    }

    @Override
    public IndustryEnergy getIndustryEnergy(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        IndustryEnergy industryEnergy=getOne(wrapper);
        return industryEnergy;
    }
}
