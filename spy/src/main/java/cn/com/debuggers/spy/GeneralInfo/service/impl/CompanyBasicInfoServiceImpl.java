package cn.com.debuggers.spy.GeneralInfo.service.impl;

import cn.com.debuggers.spy.GeneralInfo.entity.CompanyBasicInfo;
import cn.com.debuggers.spy.GeneralInfo.mapper.CompanyBasicInfoMapper;
import cn.com.debuggers.spy.GeneralInfo.service.ICompanyBasicInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nyw
 * @since 2020-07-21
 */
@Service
public class CompanyBasicInfoServiceImpl extends ServiceImpl<CompanyBasicInfoMapper, CompanyBasicInfo> implements ICompanyBasicInfoService {

    @Override
    public CompanyBasicInfo getBasicInfo(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        CompanyBasicInfo companyBasicInfo=getOne(wrapper);
        return companyBasicInfo;
    }
}
