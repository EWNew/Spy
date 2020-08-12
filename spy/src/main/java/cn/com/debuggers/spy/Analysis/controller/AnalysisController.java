package cn.com.debuggers.spy.Analysis.controller;


import cn.com.debuggers.spy.Analysis.entity.*;
import cn.com.debuggers.spy.Analysis.service.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author nyw
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/Analysis")
@CrossOrigin
public class AnalysisController {
    @Autowired
    IProvinceInfoService provinceInfoService;
    @Autowired
    IIndustryBuildingService industryBuildingService;
    @Autowired
    IIndustryEnergyService industryEnergyService;
    @Autowired
    IIndustryFinancialService industryFinancialService;
    @Autowired
    IIndustryFirstService industryFirstService;
    @Autowired
    IIndustryItService industryItService;
    @Autowired
    IIndustryHeavyService industryHeavyService;
    @Autowired
    IIndustryLightService industryLightService;
    @Autowired
    IIndustryMiningService industryMiningService;
    @Autowired
    IIndustryPublicService industryPublicService;
    @Autowired
    IIndustryScienceService industryScienceService;
    @Autowired
    IIndustryServiceService industryServiceService;
    @Autowired
    IIndustryTransportService industryTransportService;

    @RequestMapping(value = "/getProvinceInfo", method = RequestMethod.POST)
    public JSONObject getProvinceInfo(String creditCode) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (creditCode == null || creditCode.equals("")) {
                jsonObject.put("errcode", "60001");
                jsonObject.put("errmsg", "企业信用代码不能为空");
                return jsonObject;
            }
            ProvinceInfo provinceInfo = provinceInfoService.getProvinceInfo(creditCode);
            jsonObject.put("errcode", "0");
            jsonObject.put("provinceInfo", provinceInfo);
        } catch (Exception e) {
            jsonObject.put("errcode", "60002");
            jsonObject.put("errmsg", "企业省份分析信息查询失败");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/getIndustryAnalysisInfo", method = RequestMethod.POST)
    public JSONObject getIndustryAnalysisInfo(String creditCode) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (creditCode == null || creditCode.equals("")) {
                jsonObject.put("errcode", "60001");
                jsonObject.put("errmsg", "企业信用代码不能为空");
                return jsonObject;
            }
            if (industryBuildingService.hasIndustryBuilding(creditCode)){
                IndustryBuilding industryBuilding=industryBuildingService.getIndustryBuilding(creditCode);
                jsonObject.put("errcode","0");
                jsonObject.put("industry","建筑业");
                jsonObject.put("industryAnalysisInfo",industryBuilding);
            }else if (industryEnergyService.hasIndustryEnergy(creditCode)){
                IndustryEnergy industryEnergy =industryEnergyService.getIndustryEnergy(creditCode);
                jsonObject.put("errcode","0");
                jsonObject.put("industry","能源业");
                jsonObject.put("industryAnalysisInfo",industryEnergy);
            }else if (industryFinancialService.hasIndustryFinancial(creditCode)){
                IndustryFinancial industryFinancial=industryFinancialService.getIndustryFinancial(creditCode);
                jsonObject.put("errcode","0");
                jsonObject.put("industry","金融业");
                jsonObject.put("industryAnalysisInfo",industryFinancial);
            }else if(industryFirstService.hasIndustryFirst(creditCode)){
                IndustryFirst industryFirst=industryFirstService.getIndustryFirst(creditCode);
                jsonObject.put("errcode","0");
                jsonObject.put("industry","第一产业");
                jsonObject.put("industryAnalysisInfo",industryFirst);
            }else if (industryHeavyService.hasIndustryHeavy(creditCode)){
                IndustryHeavy industryHeavy=industryHeavyService.getIndustryHeavy(creditCode);
                jsonObject.put("errcode","0");
                jsonObject.put("industry","重工业");
                jsonObject.put("industryAnalysisInfo",industryHeavy);
            }else if (industryItService.hasIndustryIt(creditCode)){
                IndustryIt industryIt=industryItService.getIndustryIt(creditCode);
                jsonObject.put("errcode","0");
                jsonObject.put("industry","IT业");
                jsonObject.put("industryAnalysisInfo",industryIt);
            }else if (industryLightService.hasIndustryLight(creditCode)){
                IndustryLight industryLight=industryLightService.getIndustryLight(creditCode);
                jsonObject.put("errcode","0");
                jsonObject.put("industry","轻工业");
                jsonObject.put("industryAnalysisInfo",industryLight);
            }else if (industryMiningService.hasIndustryMining(creditCode)){
                IndustryMining industryMining=industryMiningService.getIndustryMining(creditCode);
                jsonObject.put("errcode","0");
                jsonObject.put("industry","矿物业");
                jsonObject.put("industryAnalysisInfo",industryMining);
            }else if (industryPublicService.hasIndustryPublic(creditCode)){
                IndustryPublic industryPublic=industryPublicService.getIndustryPublic(creditCode);
                jsonObject.put("errcode","0");
                jsonObject.put("industry","公告服务业");
                jsonObject.put("industryAnalysisInfo",industryPublic);
            }else if (industryScienceService.hasIndustryScience(creditCode)){
                IndustryScience industryScience=industryScienceService.getIndustryScience(creditCode);
                jsonObject.put("errcode","0");
                jsonObject.put("industry","科研业");
                jsonObject.put("industryAnalysisInfo",industryScience);
            }else if (industryServiceService.hasIndustryService(creditCode)){
                IndustryService industryService=industryServiceService.getIndustryService(creditCode);
                jsonObject.put("errcode","0");
                jsonObject.put("industry","服务业");
                jsonObject.put("industryAnalysisInfo",industryService);
            }else if (industryTransportService.hasIndustryTransport(creditCode)){
                IndustryTransport industryTransport=industryTransportService.getIndustryTransport(creditCode);
                jsonObject.put("errcode","0");
                jsonObject.put("industry","交通运输业");
                jsonObject.put("industryAnalysisInfo",industryTransport);
            }else {
                jsonObject.put("errcode","60004");
                jsonObject.put("errmsg","未找到该公司行业分析数据");
            }

        }catch (Exception e){
            jsonObject.put("errcode", "60003");
            jsonObject.put("errmsg", "企业行业分析数据查询失败");
        }
        return jsonObject;
    }
}
