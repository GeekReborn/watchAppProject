package net.sxbang.watchapp.common.exception;

import net.sxbang.watchapp.common.result.ResultCode;

public class WxUnauthorizedException extends BaseException {
    public WxUnauthorizedException() {
        super(ResultCode.USER_WX_UNAUTHORIZED_EXCEPTION);
    }
}
