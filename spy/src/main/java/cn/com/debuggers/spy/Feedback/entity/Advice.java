package cn.com.debuggers.spy.Feedback.entity;

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
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Advice implements Serializable {

    private static final long serialVersionUID = 1L;

    private String advicearea;

    private String connectionarea;

    public Advice(String advicearea,String connectionarea){
        this.advicearea=advicearea;
        this.connectionarea=connectionarea;
    }

}
