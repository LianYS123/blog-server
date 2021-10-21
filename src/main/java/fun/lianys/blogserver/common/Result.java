package fun.lianys.blogserver.common;

import fun.lianys.blogserver.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String code;
    private String message;
    private Object data;

    public static Result of(String code, String message, Object data) {
        return new Result(code, message, data);
    }

    public static Result ofSuccess(Object data) {
        return ofResultMessage(ResultMessage.RESULT_SUCCESS, data);
    }

    public static Result ofMessage(String message) {
        return of(ResultMessage.RESULT_SUCCESS.getCode(), message, null);
    }

    public static Result ofResultMessage(ResultMessage status) {
        return ofResultMessage(status, null);
    }

    public static Result ofResultMessage(ResultMessage status, Object data) {
        return of(status.getCode(), status.getMessage(), data);
    }

    public static Result ofException(BaseException exception, Object data) {
        return of(exception.getCode(), exception.getMessage(), data);
    }

    public static  Result ofException(BaseException exception) {
        return ofException(exception, null);
    }
}
