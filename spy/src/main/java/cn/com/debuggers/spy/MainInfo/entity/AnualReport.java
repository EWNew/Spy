package cn.com.debuggers.spy.MainInfo.entity;

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
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("anualReport")
public class AnualReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("creditCode")
    private String creditCode;

    @TableField("companyName")
    private String companyName;

    @TableField("nianBaoTitle")
    private String nianBaoTitle;

    @TableField("nianBaoUrl")
    private String nianBaoUrl;


}
