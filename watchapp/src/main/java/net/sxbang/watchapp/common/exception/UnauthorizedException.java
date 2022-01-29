package net.sxbang.watchapp.common.exception;

import net.sxbang.watchapp.common.result.ResultCode;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException() {
        super(ResultCode.USER_UNAUTHORIZED_EXCEPTION);
    }
}
