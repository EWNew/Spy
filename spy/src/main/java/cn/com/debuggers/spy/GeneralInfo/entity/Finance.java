package cn.com.debuggers.spy.GeneralInfo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class Finance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 统一社会信用代码
     */
    @TableId("creditCode")
    private String creditCode;

    /**
     * 公司名字
     */
    @TableField("companyName")
    private String companyName;

    /**
     * 营业收入（元）
     */
    private String income;

    /**
     * 营业利润（元）
     */
    private String profit;

    /**
     * 净利润（元）：
     */
    @TableField("netProfit")
    private String netProfit;

    /**
     * 总资产（元）
     */
    @TableField("totalAssets")
    private String totalAssets;

    /**
     * 总负债（元）
     */
    @TableField("totalLiability")
    private String totalLiability;

    /**
     * 净资产（元）
     */
    @TableField("netAssets")
    private String netAssets;


}
