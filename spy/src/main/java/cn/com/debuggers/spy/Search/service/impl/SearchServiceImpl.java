package cn.com.debuggers.spy.Search.service.impl;

import cn.com.debuggers.spy.GeneralInfo.entity.CompanyBasicInfo;
import cn.com.debuggers.spy.GeneralInfo.mapper.CompanyBasicInfoMapper;
import cn.com.debuggers.spy.Search.service.ISearchService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nyw
 * @since 2020-07-22
 */
@Service
public class SearchServiceImpl extends ServiceImpl<CompanyBasicInfoMapper, CompanyBasicInfo> implements ISearchService {
    @Override
    public List<CompanyBasicInfo> nameSearch(String name){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.like("companyName",name);
        List<CompanyBasicInfo> list=list(wrapper);
        return list;
    }

    @Override
    public List<CompanyBasicInfo> codeSearch(String code) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("creditCode",code);
        List<CompanyBasicInfo> list=list(wrapper);
        return list;
    }

}
