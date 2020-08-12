package cn.com.debuggers.spy.Search.controller;


import cn.com.debuggers.spy.GeneralInfo.entity.CompanyBasicInfo;
import cn.com.debuggers.spy.Search.service.ISearchService;
import net.sf.json.JSONArray;
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
 * @author nyw
 * @since 2020-07-22
 */
@RestController
@RequestMapping("/Search")
@CrossOrigin
public class SearchController {

    @Autowired
    private ISearchService searchService;

    @RequestMapping(value = "/nameSearch",method = RequestMethod.POST)
    public JSONObject nameSearch(String name){
        JSONObject jsonObject = new JSONObject();
        try {
            if(name==null||name.equals("")){
                jsonObject.put("errcode","30001");
                jsonObject.put("errmsg","名称查询不能为空");
                return jsonObject;
            }
            List<CompanyBasicInfo> result=searchService.nameSearch(name);
            jsonObject.put("errcode","0");
            jsonObject.put("result", result);
        }catch (Exception e){
            jsonObject.put("errcode","30002");
            jsonObject.put("errmsg","按名称查询失败");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/codeSearch",method = RequestMethod.POST)
    public JSONObject codeSearch(String code){
        JSONObject jsonObject=new JSONObject();
        try{
            if(code==null||code.equals("")){
                jsonObject.put("errcode","30003");
                jsonObject.put("errmsg","查询代码不能为空");
                return jsonObject;
            }
            List<CompanyBasicInfo> result=searchService.codeSearch(code);
            jsonObject.put("errcode","0");
            jsonObject.put("result",result);
        }catch (Exception e)
        {
            jsonObject.put("errcode","30004");
            jsonObject.put("errmsg","按代码查询失败");
        }
        return jsonObject;
    }

    }
