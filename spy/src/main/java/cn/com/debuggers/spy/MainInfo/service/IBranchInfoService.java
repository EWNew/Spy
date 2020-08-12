package cn.com.debuggers.spy.MainInfo.service;

import cn.com.debuggers.spy.MainInfo.entity.BranchInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyw
 * @since 2020-07-25
 */
public interface IBranchInfoService extends IService<BranchInfo> {
    public List<BranchInfo> getBranchInfo(String creditCode);
}
