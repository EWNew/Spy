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
 * @since 2020-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("adminCommen")
public class AdminCommen implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("creditCode")
    private String creditCode;

    @TableField("companyName")
    private String companyName;

    private String title;

    private String department;

    private String link;

    private String date;


}
