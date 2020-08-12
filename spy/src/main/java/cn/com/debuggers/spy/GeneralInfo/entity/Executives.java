package cn.com.debuggers.spy.GeneralInfo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author nyw
 * @since 2020-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Executives implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 统一社会信用代码
     */
    @TableField("creditCode")
    private String creditCode;

    /**
     * 公司名字
     */
    @TableField("companyName")
    private String companyName;

    /**
     * 高管名字
     */
    private String name;

    /**
     * 高管职位
     */
    private String job;


}
