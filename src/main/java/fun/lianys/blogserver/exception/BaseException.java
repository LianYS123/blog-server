package fun.lianys.blogserver.exception;

import fun.lianys.blogserver.common.ResultMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 异常基类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-10-02 21:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
    private String code;
    private String message;

    public BaseException(ResultMessage status) {
        super(status.getMessage());
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
