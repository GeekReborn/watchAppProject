package net.sxbang.watchapp.common.handler;

import lombok.extern.slf4j.Slf4j;
import net.sxbang.watchapp.common.exception.BaseException;
import net.sxbang.watchapp.common.result.RestResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = {BaseException.class, Exception.class})
    public RestResult errorHandler(Exception ex) {
        log.error("Exception Message:", ex);
        if (ex instanceof BaseException) {
            return RestResult.failure(((BaseException) ex).getResultCode());
        }
        return RestResult.failure();
    }

}
