package cn.com.debuggers.spy.MainInfo.service;

import cn.com.debuggers.spy.MainInfo.entity.AdminCommen;
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
public interface IAdminCommenService extends IService<AdminCommen> {
    public List<AdminCommen> getAdminCommen(String creditCode);
}
