package cn.com.debuggers.spy.GeneralInfo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("topTenHolders")
public class TopTenHolders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司统一社会信用代码
     */
    @TableField("creditCode")
    private String creditCode;

    /**
     * 公司名字
     */
    @TableField("companyName")
    private String companyName;

    /**
     * 股东编号(排名)
     */
    private String num;

    /**
     * 股东名字
     */
    private String name;

    /**
     * 持股数量
     */
    private String quantity;

    /**
     * 持股比例
     */
    private String ratio;


}
