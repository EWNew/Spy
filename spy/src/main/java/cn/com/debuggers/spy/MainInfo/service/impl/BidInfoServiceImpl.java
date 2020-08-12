package cn.com.debuggers.spy.MainInfo.service.impl;

import cn.com.debuggers.spy.MainInfo.entity.BidInfo;
import cn.com.debuggers.spy.MainInfo.mapper.BidInfoMapper;
import cn.com.debuggers.spy.MainInfo.service.IBidInfoService;
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
 * @since 2020-07-24
 */
@Service
public class BidInfoServiceImpl extends ServiceImpl<BidInfoMapper, BidInfo> implements IBidInfoService {

    @Override
    public List<BidInfo> getBidInfo(String creditCode) {
        QueryWrapper wrapper =new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        List<BidInfo> list=list(wrapper);
        return list;
    }
}
