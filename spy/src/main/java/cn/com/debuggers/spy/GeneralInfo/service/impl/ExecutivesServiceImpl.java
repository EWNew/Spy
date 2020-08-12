package cn.com.debuggers.spy.GeneralInfo.service.impl;

import cn.com.debuggers.spy.GeneralInfo.entity.Executives;
import cn.com.debuggers.spy.GeneralInfo.mapper.ExecutivesMapper;
import cn.com.debuggers.spy.GeneralInfo.service.IExecutivesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nyw
 * @since 2020-07-21
 */
@Service
public class ExecutivesServiceImpl extends ServiceImpl<ExecutivesMapper, Executives> implements IExecutivesService {

    @Override
    public List<Executives> getExecutives(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        List<Executives> list=list(wrapper);
        return list;
    }
}
