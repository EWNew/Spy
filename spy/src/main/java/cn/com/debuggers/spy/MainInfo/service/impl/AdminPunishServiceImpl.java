package cn.com.debuggers.spy.MainInfo.service.impl;

import cn.com.debuggers.spy.MainInfo.entity.AdminPunish;
import cn.com.debuggers.spy.MainInfo.mapper.AdminPunishMapper;
import cn.com.debuggers.spy.MainInfo.service.IAdminPunishService;
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
 * @since 2020-07-27
 */
@Service
public class AdminPunishServiceImpl extends ServiceImpl<AdminPunishMapper, AdminPunish> implements IAdminPunishService {

    @Override
    public List<AdminPunish> getAdminPunish(String creditCode) {
        QueryWrapper wrapper =new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        List<AdminPunish> list=list(wrapper);
        return list;
    }
}
