package cn.com.debuggers.spy.Analysis.entity;

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
 * @since 2020-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Industry_heavy")
public class IndustryHeavy implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("creditCode")
    private String creditCode;

    @TableField("companyName")
    private String companyName;

    @TableField("regCapital")
    private String regCapital;

    @TableField("estabDate")
    private String estabDate;

    @TableField("businessTerm")
    private String businessTerm;

    @TableField("Capitalrank")
    private String Capitalrank;

    private String estabrank;

    @TableField("netProfit")
    private String netProfit;

    @TableField("netAssets")
    private String netAssets;

    @TableField("Profitrank")
    private String Profitrank;

    @TableField("Assetsrank")
    private String Assetsrank;

    private String total;


}
