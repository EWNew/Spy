package cn.com.debuggers.spy.Analysis.service.impl;

import cn.com.debuggers.spy.Analysis.entity.ProvinceInfo;
import cn.com.debuggers.spy.Analysis.mapper.ProvinceInfoMapper;
import cn.com.debuggers.spy.Analysis.service.IProvinceInfoService;
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
public class ProvinceInfoServiceImpl extends ServiceImpl<ProvinceInfoMapper, ProvinceInfo> implements IProvinceInfoService {

    @Override
    public ProvinceInfo getProvinceInfo(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        ProvinceInfo provinceInfo=getOne(wrapper);
        return provinceInfo;
    }
}
