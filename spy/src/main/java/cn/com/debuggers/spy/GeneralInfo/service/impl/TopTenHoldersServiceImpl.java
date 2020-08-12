package cn.com.debuggers.spy.GeneralInfo.service.impl;

import cn.com.debuggers.spy.GeneralInfo.entity.TopTenHolders;
import cn.com.debuggers.spy.GeneralInfo.mapper.TopTenHoldersMapper;
import cn.com.debuggers.spy.GeneralInfo.service.ITopTenHoldersService;
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
public class TopTenHoldersServiceImpl extends ServiceImpl<TopTenHoldersMapper, TopTenHolders> implements ITopTenHoldersService {

    @Override
    public List<TopTenHolders> getTopTenHolders(String creditCode) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        List<TopTenHolders> list =list(wrapper);
        return list;
    }
}
