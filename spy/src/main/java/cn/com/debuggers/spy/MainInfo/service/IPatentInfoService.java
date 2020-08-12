package cn.com.debuggers.spy.MainInfo.service;

import cn.com.debuggers.spy.MainInfo.entity.PatentInfo;
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
public interface IPatentInfoService extends IService<PatentInfo> {
    public List<PatentInfo> getPatentInfo(String creditCode);
}
