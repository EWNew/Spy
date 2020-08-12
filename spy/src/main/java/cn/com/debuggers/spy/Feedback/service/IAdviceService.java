package cn.com.debuggers.spy.Feedback.service;

import cn.com.debuggers.spy.Feedback.entity.Advice;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyw
 * @since 2020-07-28
 */
public interface IAdviceService extends IService<Advice> {
public boolean advice(String advicearea,String connectionarea);
}
