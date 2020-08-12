package cn.com.debuggers.spy.GeneralInfo.controller;


import cn.com.debuggers.spy.GeneralInfo.entity.CompanyBasicInfo;
import cn.com.debuggers.spy.GeneralInfo.entity.Executives;
import cn.com.debuggers.spy.GeneralInfo.entity.Finance;
import cn.com.debuggers.spy.GeneralInfo.entity.TopTenHolders;
import cn.com.debuggers.spy.GeneralInfo.service.ICompanyBasicInfoService;
import cn.com.debuggers.spy.GeneralInfo.service.IExecutivesService;
import cn.com.debuggers.spy.GeneralInfo.service.IFinanceService;
import cn.com.debuggers.spy.GeneralInfo.service.ITopTenHoldersService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author nyw
 * @since 2020-07-21
 */
@RestController
@RequestMapping("/GeneralInfo")
@CrossOrigin

public class GeneralInfoController {

    @Autowired
    private ICompanyBasicInfoService companyBasicInfoService;
    @Autowired
    private ITopTenHoldersService topTenHoldersService;
    @Autowired
    private IExecutivesService executivesService;
    @Autowired
    private IFinanceService financeService;

    @RequestMapping(value = "/getBasicInfo", method = RequestMethod.POST)
    public JSONObject getBasicInfo(String creditCode) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (creditCode == null || creditCode.equals("")) {
                jsonObject.put("errcode", "40001");
                jsonObject.put("errmsg", "公司代码不能为空");
                return jsonObject;
            }
            CompanyBasicInfo companyBasicInfo = companyBasicInfoService.getBasicInfo(creditCode);
            jsonObject.put("errcode", "0");
            jsonObject.put("CompanyBasicInfo", companyBasicInfo);
        } catch (Exception e) {
            jsonObject.put("errcode", "40002");
            jsonObject.put("errmsg", "公司基本信息查询错误");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/getTopTenHolders", method = RequestMethod.POST)
    public JSONObject getTopTenHolders(String creditCode) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (creditCode == null || creditCode.equals("")) {
                jsonObject.put("errcode", "40001");
                jsonObject.put("errmsg", "公司代码不能为空");
                return jsonObject;
            }
            List<TopTenHolders> list = topTenHoldersService.getTopTenHolders(creditCode);
            jsonObject.put("errcode", "0");
            jsonObject.put("topTenHolders", list);
        } catch (Exception e) {
            jsonObject.put("errcode", "40003");
            jsonObject.put("errmsg", "公司主要股东查询错误");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/getExecutives", method = RequestMethod.POST)
    public JSONObject getExecutives(String creditCode) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (creditCode == null || creditCode.equals("")) {
                jsonObject.put("errcode", "40001");
                jsonObject.put("errmsg", "公司代码不能为空");
                return jsonObject;
            }
            List<Executives> list = executivesService.getExecutives(creditCode);
            jsonObject.put("errcode", "0");
            jsonObject.put("executives", list);
        } catch (Exception e) {
            jsonObject.put("errcode", "40004");
            jsonObject.put("errmsg", "公司高管查询错误");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/getFinance", method = RequestMethod.POST)
    public JSONObject getFinance(String creditCode) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (creditCode == null || creditCode.equals("")) {
                jsonObject.put("errcode", "40001");
                jsonObject.put("errmsg", "公司代码不能为空");
                return jsonObject;
            }
            Finance finance=financeService.getFinance(creditCode);
            jsonObject.put("errcode", "0");
            jsonObject.put("finance",finance);
        } catch (Exception e) {
            jsonObject.put("errcode","40005");
            jsonObject.put("errmsg","财务指标数据查询失败");
        }
        return jsonObject;
    }

}
