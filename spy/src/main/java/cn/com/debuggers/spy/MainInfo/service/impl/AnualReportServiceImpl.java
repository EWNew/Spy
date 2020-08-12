package cn.com.debuggers.spy.MainInfo.service.impl;

import cn.com.debuggers.spy.MainInfo.entity.AnualReport;
import cn.com.debuggers.spy.MainInfo.mapper.AnualReportMapper;
import cn.com.debuggers.spy.MainInfo.service.IAnualReportService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nyw
 * @since 2020-07-23
 */
@Service
public class AnualReportServiceImpl extends ServiceImpl<AnualReportMapper, AnualReport> implements IAnualReportService {

    @Override
    public List<AnualReport> getAnualReport(String creditCode) {
        QueryWrapper wrapper =new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        wrapper.select("distinct creditCode,nianBaoTitle,companyName,nianBaoUrl");
        List<AnualReport> list=list(wrapper);
        return list;
    }
}
