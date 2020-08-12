package cn.com.debuggers.spy.userInfo.entity;

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
 * @author spf
 * @since 2020-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("userInfo")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("phoneNumber")
    private String phoneNumber;

    @TableField("userName")
    private String userName;

    private String password;

    private Integer permission;

    public UserInfo(String p,String n,String pwd){
        phoneNumber=p;
        userName=n;
        password=pwd;
    }

    public String getPwd(){
        return password;
    }
}
