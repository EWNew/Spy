package cn.com.debuggers.spy.userInfo.controller;


import cn.com.debuggers.spy.userInfo.entity.UserInfo;
import cn.com.debuggers.spy.userInfo.service.IUserInfoService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author spf
 * @since 2020-07-21
 */
@RestController
@RequestMapping("/userInfo")
@CrossOrigin
public class UserInfoController {
    @Autowired
    private IUserInfoService userInfoService;


    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    public JSONObject getUserList() {
        JSONObject jsonObj=new JSONObject();
        try {
            List<UserInfo> userList=userInfoService.list();
            jsonObj.put("errcode","0");
            jsonObj.put("data",userList);
        }catch (Exception ex){
            jsonObj.put("errcode","20001");
            jsonObj.put("errmsg","ex.getMessage()");
        }
        return jsonObj;
    }

    @RequestMapping(value = "/userRegis",method = RequestMethod.POST)
    public JSONObject userRegis(String phone,String name,String pwd){
        JSONObject jsonObj=new JSONObject();
        try {
            userInfoService.register(phone,name,pwd);
            jsonObj.put("errcode","0");
            jsonObj.put("data",phone+" "+name+" "+pwd);
        }catch (Exception ex){
            jsonObj.put("errcode","20001");
            jsonObj.put("errmsg","Regis error");
        }
        return jsonObj;
    }

    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public JSONObject userLogin(String name,String pwd){
        JSONObject jsonObj=new JSONObject();
        try {
            if(userInfoService.login(name,pwd)==true) {
                jsonObj.put("errcode", "0");
                jsonObj.put("data", name + "登陆成功！");
            }else {
                jsonObj.put("errcode","1");
                jsonObj.put("data","密码错误！");
            }
        }catch (Exception ex){
            jsonObj.put("errcode","20001");
            jsonObj.put("errmsg","登录失败");
        }
        return jsonObj;
    }
}