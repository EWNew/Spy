package cn.com.debuggers.spy.MainInfo.service.impl;

import cn.com.debuggers.spy.MainInfo.entity.JudgementDocument;
import cn.com.debuggers.spy.MainInfo.mapper.JudgementDocumentMapper;
import cn.com.debuggers.spy.MainInfo.service.IJudgementDocumentService;
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
 * @since 2020-07-27
 */
@Service
public class JudgementDocumentServiceImpl extends ServiceImpl<JudgementDocumentMapper, JudgementDocument> implements IJudgementDocumentService {

    @Override
    public List<JudgementDocument> getJudgementDocument(String creditCode) {
        QueryWrapper wrapper =new QueryWrapper();
        wrapper.eq("creditCode",creditCode);
        List<JudgementDocument> list =list(wrapper);
        return list;
    }
}
