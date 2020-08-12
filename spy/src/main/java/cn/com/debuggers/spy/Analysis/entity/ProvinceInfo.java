package cn.com.debuggers.spy.Analysis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2020-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("provinceInfo")
public class ProvinceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("companyName")
    private String companyName;

    @TableField("creditCode")
    private String creditCode;

    private String province;

    @TableField("regCapital")
    private String regCapital;

    @TableField("estabDate")
    private String estabDate;

    @TableField("capitalRank")
    private String capitalRank;

    @TableField("dateRank")
    private String dateRank;

    @TableField("netProfit")
    private String netProfit;

    @TableField("netAssets")
    private String netAssets;

    @TableField("profitRank")
    private String profitRank;

    @TableField("assetsRank")
    private String assetsRank;

    @TableField("companyNumber")
    private String companyNumber;


}
