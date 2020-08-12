package cn.com.debuggers.spy.Feedback.controller;


import cn.com.debuggers.spy.Feedback.service.IAdviceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nyw
 * @since 2020-07-28
 */
@RestController
@RequestMapping("/Feedback")
@CrossOrigin
public class AdviceController {

    @Autowired
    IAdviceService adviceService;

    @RequestMapping(value = "/advice",method = RequestMethod.POST)
    public JSONObject advice(String advicearea,String connectionarea){
        JSONObject jsonObject=new JSONObject();
        try{
            if(advicearea==null||advicearea.equals("")){
                jsonObject.put("errcode","70001");
                jsonObject.put("errmsg","反馈意见不能为空");
                return jsonObject;
            }
            if (connectionarea==null||connectionarea.equals("")){
                jsonObject.put("errcode","70002");
                jsonObject.put("errmsg","反馈意见联系方式不能为空");
                return jsonObject;
            }
            if(adviceService.advice(advicearea,connectionarea)){
                jsonObject.put("errcode","0");
                jsonObject.put("feedback","success");
            }else {
                jsonObject.put("errcode","1");
                jsonObject.put("feedback","false");
            }
        }catch (Exception e){
            jsonObject.put("errcode","70003");
            jsonObject.put("errmsg","反馈过程出现错误");
        }
        return jsonObject;
    }
}
