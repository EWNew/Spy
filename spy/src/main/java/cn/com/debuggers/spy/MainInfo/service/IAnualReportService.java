package cn.com.debuggers.spy.MainInfo.service;

import cn.com.debuggers.spy.MainInfo.entity.AnualReport;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyw
 * @since 2020-07-23
 */
public interface IAnualReportService extends IService<AnualReport> {
    public List<AnualReport> getAnualReport(String creditCode);
}
