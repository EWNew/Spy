package cn.com.debuggers.spy.Analysis.service.impl;

import cn.com.debuggers.spy.Analysis.entity.IndustryTransport;
import cn.com.debuggers.spy.Analysis.mapper.IndustryTransportMapper;
import cn.com.debuggers.spy.Analysis.service.IIndustryTransportService;
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
public class IndustryTransportServiceImpl extends ServiceImpl<IndustryTransportMapper, IndustryTransport> implements IIndustryTransportService {

    @Override
    public boolean hasIndustryTransport(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        if(count(wrapper)==0)return false;
        return true;
    }

    @Override
    public IndustryTransport getIndustryTransport(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        IndustryTransport industryTransport=getOne(wrapper);
        return industryTransport;
    }
}
