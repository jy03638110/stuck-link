package top.stuck.link.core.response;

import java.io.Serializable;

/**
 *
 * Created on 2020-04-28
 *
 * @author Octopus
 */
public class ReturnT<T> implements Serializable {

    private static final long serialVersionUID = 4907668910544617452L;

    /**
     * 请求成功
     */
    public static final int SUCCESS_CODE = 200;

    /**
     * 请求失败
     */
    public static final int FAIL_CODE = 500;

    /**
     * 参数校验失败
     */
    public static final int PARAM_VALIDATE_FAIL = 301;

    public static final ReturnT<String> SUCCESS = new ReturnT<>();

    public static final ReturnT<String> FAIL = new ReturnT<>(FAIL_CODE, null);

    private int code;

    private String msg;

    private T content;

    public ReturnT(){
        code = SUCCESS_CODE;
    }

    public ReturnT(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ReturnT(T content) {
        this.code = SUCCESS_CODE;
        this.content = content;
    }

    public boolean success(){
        return SUCCESS.equals(code);
    }

    public boolean fail(){
        return !success();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ReturnT [code=" + code + ", msg=" + msg + ", content=" + content + "]";
    }
}
