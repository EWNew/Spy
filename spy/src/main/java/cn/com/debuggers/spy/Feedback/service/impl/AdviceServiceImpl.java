package cn.com.debuggers.spy.Feedback.service.impl;

import cn.com.debuggers.spy.Feedback.entity.Advice;
import cn.com.debuggers.spy.Feedback.mapper.AdviceMapper;
import cn.com.debuggers.spy.Feedback.service.IAdviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nyw
 * @since 2020-07-28
 */
@Service
public class AdviceServiceImpl extends ServiceImpl<AdviceMapper, Advice> implements IAdviceService {

    @Override
    public boolean advice(String advicearea, String connectionarea) {
        Advice advice=new Advice(advicearea,connectionarea);
        return save(advice);
    }
}
