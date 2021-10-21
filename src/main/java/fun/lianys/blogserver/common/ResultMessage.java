package fun.lianys.blogserver.common;


public enum ResultMessage {
    RESULT_SUCCESS("0000", "请求成功"),
    INVALID_PARAMS("1003", "非法参数"),
    FAIL("1111", "操作失败"),
    UNAUTHENTICATED("1001", "此操作需要登录"),
    UNAUTHORIZED("1002", "权限不足"),
    SERVER_ERROR("9999", "服务器异常，请稍后重试");
    private String code;
    private String message;

    public String getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }

    ResultMessage(String code, String message){
        this.code = code;
        this.message = message;
    }
}
