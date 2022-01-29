package net.sxbang.watchapp.common.exception;

import net.sxbang.watchapp.common.result.ResultCode;

/**
 * @program: watchapp
 * @description: 公共BaseException异常类
 * @author: AndyZheng
 * @create: 2019-09-30 17:05
 **/
public abstract class BaseException extends Exception {

    protected ResultCode resultCode = ResultCode.FAIL;

    public BaseException(ResultCode resultCode) {
        super();
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return this.resultCode;
    }
}
