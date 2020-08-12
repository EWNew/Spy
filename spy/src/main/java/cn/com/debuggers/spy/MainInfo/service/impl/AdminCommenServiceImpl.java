package cn.com.debuggers.spy.MainInfo.service.impl;

import cn.com.debuggers.spy.MainInfo.entity.AdminCommen;
import cn.com.debuggers.spy.MainInfo.mapper.AdminCommenMapper;
import cn.com.debuggers.spy.MainInfo.service.IAdminCommenService;
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
public class AdminCommenServiceImpl extends ServiceImpl<AdminCommenMapper, AdminCommen> implements IAdminCommenService {

    @Override
    public List<AdminCommen> getAdminCommen(String creditCode) {
        QueryWrapper wrapper =new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        List<AdminCommen> list =list(wrapper);
        return list;
    }
}
