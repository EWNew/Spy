package cn.com.debuggers.spy.GeneralInfo.entity;

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
 * @since 2020-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("CompanyBasicInfo")
public class CompanyBasicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司名
     */
    @TableId("companyName")
    private String companyName;
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 统一社会信用代码
     */
    @TableField("creditCode")
    private String creditCode;
    public String getCreditCode() {
        return creditCode;
    }

    /**
     * 行业
     */
    private String industry;

    /**
     * 注册地址
     */
    @TableField("regisAdress")
    private String regisAdress;
    public String getRegisAdress() {
        return regisAdress;
    }

    /**
     * 注册资本
     */
    @TableField("regCapital")
    private String regCapital;
    public String getRegCapital() {
        return regCapital;
    }

    /**
     * 成立日期
     */
    @TableField("estabDate")
    private String estabDate;

    /**
     * 公司网址
     */
    private String website;
    public String getWebsite() {
        return website;
    }

    /**
     * 邮政编码
     */
    private String postcode;

    /**
     * 法人
     */
    @TableField("legalRepresentative")
    private String legalRepresentative;
    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    /**
     * 公司电话
     */
    private String phone;
    public String getPhone() {
        return phone;
    }

    /**
     * 公司类型
     */
    @TableField("companyType")
    private String companyType;

    /**
     * 营业期限
     */
    @TableField("businessTerm")
    private String businessTerm;


}
