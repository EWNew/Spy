package cn.com.debuggers.spy.MainInfo.entity;

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
public class JudgementDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("creditCode")
    private String creditCode;

    private String companyname;

    private String namelist;

    private String namelink;

    private String court;

    @TableField("Riqi")
    private String Riqi;


}
