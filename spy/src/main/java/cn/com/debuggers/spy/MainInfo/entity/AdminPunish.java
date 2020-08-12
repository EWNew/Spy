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
 * @since 2020-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("adminPunish")
public class AdminPunish implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("companyName")
    private String companyName;

    @TableField("creditCode")
    private String creditCode;

    private String title;

    private String link;

    private String department;

    private String date;


}
