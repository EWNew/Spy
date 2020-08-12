package cn.com.debuggers.spy.Analysis.service;

import cn.com.debuggers.spy.Analysis.entity.ProvinceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyw
 * @since 2020-07-27
 */
public interface IProvinceInfoService extends IService<ProvinceInfo> {
public ProvinceInfo getProvinceInfo(String creditCode);
}
