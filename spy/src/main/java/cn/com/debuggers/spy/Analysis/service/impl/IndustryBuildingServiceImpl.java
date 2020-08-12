package cn.com.debuggers.spy.Analysis.service.impl;

import cn.com.debuggers.spy.Analysis.entity.IndustryBuilding;
import cn.com.debuggers.spy.Analysis.mapper.IndustryBuildingMapper;
import cn.com.debuggers.spy.Analysis.service.IIndustryBuildingService;
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
public class IndustryBuildingServiceImpl extends ServiceImpl<IndustryBuildingMapper, IndustryBuilding> implements IIndustryBuildingService {

    @Override
    public boolean hasIndustryBuilding(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        if(count(wrapper)==0)return false;
        return true;
    }

    @Override
    public IndustryBuilding getIndustryBuilding(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        IndustryBuilding industryBuilding=getOne(wrapper);
        return industryBuilding;
    }
}
