package cn.com.debuggers.spy.userInfo.service.impl;

import cn.com.debuggers.spy.userInfo.entity.UserInfo;
import cn.com.debuggers.spy.userInfo.mapper.UserInfoMapper;
import cn.com.debuggers.spy.userInfo.service.IUserInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author spf
 * @since 2020-07-21
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
    @Override
    public void register(String phone, String name, String pwd) {
        QueryWrapper wrapper=new QueryWrapper();
        UserInfo userInfo=new UserInfo(phone,name,EncodeByMD5(pwd));
        save(userInfo);
    }

    @Override
    public boolean login(String name, String pwd) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("userName",name);
        UserInfo userInfo=getOne(wrapper);
//        System.out.println(userInfo.getPwd());
//        System.out.println(pwd);
//        System.out.println(userInfo.getPwd().equals(pwd));
        if(checkPassword(pwd,userInfo.getPwd())){
            return true;
        }else {
            return false;
        }
    }

    //MD5密码加密
    public String EncodeByMD5(String str) {
        try {
            MessageDigest md5=MessageDigest.getInstance("MD5");
            BASE64Encoder base64Encoder=new BASE64Encoder();
            String newStr=base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
            return newStr;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    //MD5密码检测
    public boolean checkPassword(String pwd,String md5){
        if(EncodeByMD5(pwd).equals(md5))
            return true;
        else
            return false;
    }
//    public static void main(String[] args){
//        String src="123456";
//        UserInfoServiceImpl userInfoService=new UserInfoServiceImpl();
//        System.out.println(userInfoService.EncodeByMD5(src));
//    }
}