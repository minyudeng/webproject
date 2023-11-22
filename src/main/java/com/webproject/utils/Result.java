package com.webproject.utils;


public class Result {
    public static final String OK = "200";
    public static final String SYS_ERROR = "500";
    private String code;
    private String msg;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
    public static Result success(){
        return new Result(OK,"请求成功", null);
    }
    public static Result success(Object o){
        return new Result(OK, "请求成功", o);
    }
    public static Result successMsg(String msg){
        return new Result(OK, msg, null);
    }
    public static Result success(String msg, Object o){
        return new Result(OK,msg,o);
    }
    public static Result error(){
        return new Result(SYS_ERROR, "系统错误", null);
    }

    public static Result error(String msg){
        return new Result(SYS_ERROR, msg, null);
    }
    public static Result error(String msg,Object o){
        return new Result(SYS_ERROR, msg, o);
    }
    public static Result error(String code,String msg, Object o){
        return new Result(code,msg,o);
    }
    public static Result error(String code,String msg){
        return new Result(code,msg,null);
    }
}
