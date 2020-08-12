package cn.com.debuggers.spy.MainInfo.service;

import cn.com.debuggers.spy.MainInfo.entity.JudgementDocument;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nyw
 * @since 2020-07-27
 */
public interface IJudgementDocumentService extends IService<JudgementDocument> {
public List<JudgementDocument> getJudgementDocument(String creditCode);
}
