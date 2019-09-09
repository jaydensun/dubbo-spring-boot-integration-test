package com.jayden.dsbit;

/**
 * @author sunyongjun
 * @since 2019/9/9
 */
public class HelloResponse {
    private String msg;

    public HelloResponse(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "HelloResponse{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
