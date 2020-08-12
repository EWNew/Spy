package cn.com.debuggers.spy.GeneralInfo.service;

import cn.com.debuggers.spy.GeneralInfo.entity.Executives;
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
public interface IExecutivesService extends IService<Executives> {

    public List<Executives> getExecutives(String creditCode);

}
