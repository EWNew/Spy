package cn.com.debuggers.spy.MainInfo.controller;


import cn.com.debuggers.spy.MainInfo.entity.*;
import cn.com.debuggers.spy.MainInfo.service.*;
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
 * @since 2020-07-23
 */
@RestController
@RequestMapping("/MainInfo")
@CrossOrigin
public class MainInfoController {

    @Autowired
    IAnualReportService anualReportService;
    @Autowired
    IBidInfoService bidInfoService;
    @Autowired
    IAdminCommenService adminCommenService;
    @Autowired
    IBranchInfoService branchInfoService;
    @Autowired
    IPatentInfoService patentInfoService;
    @Autowired
    IJudgementDocumentService judgementDocumentService;
    @Autowired
    IAdminPunishService adminPunishService;

    @RequestMapping(value = "/getAnualReport",method = RequestMethod.POST)
    public JSONObject getAnualReport(String creditCode){
        JSONObject jsonObject=new JSONObject();
        try{
            if(creditCode==null||creditCode.equals("")){
                jsonObject.put("errcode","50001");
                jsonObject.put("errmsg","企业信用代码不能为空");
                return jsonObject;
            }
            List<AnualReport> list=anualReportService.getAnualReport(creditCode);
            jsonObject.put("errcode","0");
            jsonObject.put("annualReport",list);
        }catch (Exception e){
            jsonObject.put("errcode","50002");
            jsonObject.put("errmsg","查询企业年报信息失败");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/getBidInfo",method = RequestMethod.POST)
    public JSONObject getBidInfo(String creditCode){
        JSONObject jsonObject=new JSONObject();
        try{
            if (creditCode==null||creditCode.equals("")){
                jsonObject.put("errcode","50001");
                jsonObject.put("errmsg","企业信用代码不能为空");
                return jsonObject;
            }
            List<BidInfo> list=bidInfoService.getBidInfo(creditCode);
            jsonObject.put("errcode","0");
            jsonObject.put("bidding_info",list);
        }catch (Exception e){
            jsonObject.put("errcode","50003");
            jsonObject.put("errmsg","查询企业招标信息失败");
        }
        return jsonObject;
    }
    @RequestMapping(value = "/getAdminCommen",method = RequestMethod.POST)
    public JSONObject getAdminCommen(String creditCode){
        JSONObject jsonObject=new JSONObject();
        try{
            if(creditCode==null||creditCode.equals("")){
                jsonObject.put("errcode","50001");
                jsonObject.put("errmsg","企业信用代码不能为空");
                return jsonObject;
            }
            List<AdminCommen> list=adminCommenService.getAdminCommen(creditCode);
            jsonObject.put("errcode","0");
            jsonObject.put("adminCommen",list);

        }catch (Exception e){
            jsonObject.put("errcode","50004");
            jsonObject.put("errmsg","查询企业行政表彰错误");
        }
        return  jsonObject;
    }
    @RequestMapping(value = "/getBranchInfo",method = RequestMethod.POST)
    public JSONObject getBranchInfo(String creditCode){
        JSONObject jsonObject=new JSONObject();
        try{
            if(creditCode==null||creditCode.equals("")){
                jsonObject.put("errcode","50001");
                jsonObject.put("errmsg","企业信用代码不能为空");
                return jsonObject;
            }
            List<BranchInfo> list =branchInfoService.getBranchInfo(creditCode);
            jsonObject.put("errcode","0");
            jsonObject.put("branchInfo",list);
        }catch (Exception e){
            jsonObject.put("errcode","50005");
            jsonObject.put("errmsg","查询企业分支机构信息错误");
        }
        return jsonObject;
    }
    @RequestMapping(value = "/getPatentInfo",method = RequestMethod.POST)
    public JSONObject getPatentInfo(String creditCode){
        JSONObject jsonObject=new JSONObject();
        try{
            if(creditCode==null||creditCode.equals("")){
                jsonObject.put("errcode","50001");
                jsonObject.put("errmsg","企业信用代码不能为空");
                return jsonObject;
            }
            List<PatentInfo> list =patentInfoService.getPatentInfo(creditCode);
            jsonObject.put("errcode","0");
            jsonObject.put("patent_info",list);
        }catch (Exception e){
            jsonObject.put("errcode","50006");
            jsonObject.put("errmsg","查询企业专利信息错误");
        }
        return  jsonObject;
    }
    @RequestMapping(value = "/getJudgementDocument",method = RequestMethod.POST)
    public JSONObject JudgementDocument(String creditCode){
        JSONObject jsonObject=new JSONObject();
        try{
            if(creditCode==null||creditCode.equals("")){
                jsonObject.put("errcode","50001");
                jsonObject.put("errmsg","企业信用代码不能为空");
                return jsonObject;
            }
            List<JudgementDocument> list= judgementDocumentService.getJudgementDocument(creditCode);
            jsonObject.put("errcode","0");
            jsonObject.put("judgement_document",list);
        }catch (Exception e){
            jsonObject.put("errcode","50007");
            jsonObject.put("errmsg","查询企业法院判决信息失败");
        }
        return  jsonObject;
    }
    @RequestMapping(value = "/getAdminPunish",method = RequestMethod.POST)
    public JSONObject getAdminPunish(String creditCode){
        JSONObject jsonObject=new JSONObject();
        try{
            if(creditCode==null||creditCode.equals("")){
                jsonObject.put("errcode","50001");
                jsonObject.put("errmsg","企业信用代码不能为空");
                return jsonObject;
            }
            List<AdminPunish> list =adminPunishService.getAdminPunish(creditCode);
            jsonObject.put("errcode","0");
            jsonObject.put("adminPunish",list);
        }catch (Exception e){
            jsonObject.put("errcode","50008");
            jsonObject.put("errmsg","查询企业行政处罚信息失败");
        }
        return  jsonObject;
    }
}
