package xyz.rootlab.common.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private int code;
    private Object data;
    private String msg;

    public void setResponse(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    public void setResponse(ResponseCode responseCode, String str) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg() + "( " + str + " )";
    }
}
