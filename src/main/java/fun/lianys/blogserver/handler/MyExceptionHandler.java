package fun.lianys.blogserver.handler;

import fun.lianys.blogserver.common.Result;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice // 增强controller
@Slf4j
public class MyExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handleValidationExceptions(MethodArgumentNotValidException ex) {
        HashMap<String, String> errors = new HashMap<>();
        // Set<String> errors = new HashSet<>();
        // StringBuffer errors = new StringBuffer();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            // log.error(errorMessage);
            // errors.append(errorMessage);
            errors.put(fieldName, errorMessage);
            // errors.append(s)
        });
        return Result.of("1003", errors.toString(), null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Result handleParamsMissExceptions(MissingServletRequestParameterException ex) {
        log.error(ex.getMessage());
        return Result.of("1003", "缺少必填参数", null);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result handleParamsExceptions(BindException ex) {
        log.error(ex.getMessage());
        return Result.of("1003", "参数错误", null);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(SecurityException.class)
    @ResponseBody
    public Result handleUnAuthorized(SecurityException ex) {
        return Result.of("1004", ex.getMessage(), null);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result globalErrorHandler(Exception exception) {
        log.error("【Exception】:{}", exception.getMessage());
        return Result.of("1002", "Internal Server Error", null);
    }

}
