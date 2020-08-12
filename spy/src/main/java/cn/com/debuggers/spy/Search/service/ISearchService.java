package cn.com.debuggers.spy.Search.service;

import cn.com.debuggers.spy.GeneralInfo.entity.CompanyBasicInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyw
 * @since 2020-07-22
 */
public interface ISearchService extends IService<CompanyBasicInfo> {

    public List<CompanyBasicInfo> nameSearch(String name);
    public List<CompanyBasicInfo> codeSearch(String code);
}
