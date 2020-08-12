package cn.com.debuggers.spy.GeneralInfo.service;

import cn.com.debuggers.spy.GeneralInfo.entity.CompanyBasicInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyw
 * @since 2020-07-21
 */
public interface ICompanyBasicInfoService extends IService<CompanyBasicInfo> {

    public CompanyBasicInfo getBasicInfo(String creditCode);
}
