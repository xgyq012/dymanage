package com.base.springmvc.exception;

/**
 * @author Will WM. Zhang
 * @since 2017-02-12 17:00
 */
public class ServiceException extends Exception {

    private Object resultCode = 1;
    private Object resultMsg = "业务异常";
    private Object result = null;

    public ServiceException() {
        super();
    }

    public ServiceException(Object resultCode, Object resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public ServiceException(Object resultCode, Object resultMsg, Object result) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.result = result;
    }

    public Object getResultCode() {
        return resultCode;
    }

    public void setResultCode(Object resultCode) {
        this.resultCode = resultCode;
    }

    public Object getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(Object resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
