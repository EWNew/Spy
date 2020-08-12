package cn.com.debuggers.spy.MainInfo.service.impl;

import cn.com.debuggers.spy.MainInfo.entity.PatentInfo;
import cn.com.debuggers.spy.MainInfo.mapper.PatentInfoMapper;
import cn.com.debuggers.spy.MainInfo.service.IPatentInfoService;
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
 * @since 2020-07-25
 */
@Service
public class PatentInfoServiceImpl extends ServiceImpl<PatentInfoMapper, PatentInfo> implements IPatentInfoService {

    @Override
    public List<PatentInfo> getPatentInfo(String creditCode) {
        QueryWrapper wrapper =new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        List<PatentInfo> list = list(wrapper);
        return list;
    }
}
