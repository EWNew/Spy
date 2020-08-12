package cn.com.debuggers.spy.MainInfo.service.impl;

import cn.com.debuggers.spy.MainInfo.entity.BranchInfo;
import cn.com.debuggers.spy.MainInfo.mapper.BranchInfoMapper;
import cn.com.debuggers.spy.MainInfo.service.IBranchInfoService;
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
public class BranchInfoServiceImpl extends ServiceImpl<BranchInfoMapper, BranchInfo> implements IBranchInfoService {

    @Override
    public List<BranchInfo> getBranchInfo(String creditCode) {
        QueryWrapper wrapper =new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        List<BranchInfo> list=list(wrapper);
        return list;
    }
}
