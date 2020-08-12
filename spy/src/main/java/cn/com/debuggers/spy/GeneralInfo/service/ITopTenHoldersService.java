package cn.com.debuggers.spy.GeneralInfo.service;

import cn.com.debuggers.spy.GeneralInfo.entity.TopTenHolders;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyw
 * @since 2020-07-21
 */
public interface ITopTenHoldersService extends IService<TopTenHolders> {

    public List<TopTenHolders> getTopTenHolders(String creditCode);
}
