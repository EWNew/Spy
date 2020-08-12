package cn.com.debuggers.spy.MainInfo.service;

import cn.com.debuggers.spy.MainInfo.entity.BidInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyw
 * @since 2020-07-24
 */
public interface IBidInfoService extends IService<BidInfo> {
    public List<BidInfo> getBidInfo(String creditCode);
}
