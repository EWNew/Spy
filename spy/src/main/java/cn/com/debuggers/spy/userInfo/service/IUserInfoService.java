package cn.com.debuggers.spy.userInfo.service;

import cn.com.debuggers.spy.userInfo.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author spf
 * @since 2020-07-21
 */
public interface IUserInfoService extends IService<UserInfo> {
    public void register(String phone,String name,String pwd);

    boolean login(String name, String pwd);
    public String EncodeByMD5(String str);
    public boolean checkPassword(String pwd,String md5);
}
