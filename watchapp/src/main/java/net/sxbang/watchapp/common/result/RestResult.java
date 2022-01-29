package net.sxbang.watchapp.common.result;

import org.springframework.data.domain.Pageable;

public class RestResult<T> {
    private int code;   // 请求状态码
    private String msg; // 请求状态信息
    private T data;     // 数据（可以是对象、也可以是集合）

    // 分页信息
    private long count;     // 总数据量
    private long totalPage; // 总页数
    private long pageSize;  // 每页行数
    private long currentPage;   // 当前页码

    public static <T> RestResult<T> failure() {
        RestResult<T> result = new RestResult<T>();
        result.setResultCode(ResultCode.FAIL);
        return result;
    }

    public static <T> RestResult<T> failure(T data) {
        RestResult<T> result = new RestResult<T>();
        result.setResultCode(ResultCode.FAIL);
        result.setData(data);
        return result;
    }

    public static <T> RestResult<T> failure(ResultCode resultCode) {
        RestResult<T> result = new RestResult<T>();
        result.setResultCode(resultCode);
        return result;
    }

    public static <T> RestResult<T> failure(ResultCode resultCode, T data) {
        RestResult<T> result = new RestResult<T>();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public static <T> RestResult<T> success() {
        RestResult<T> result = new RestResult<T>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static <T> RestResult<T> success(T data) {
        RestResult<T> result = new RestResult<T>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }
    public static <T> RestResult<T> success(T data, Integer count) {
        RestResult<T> result = new RestResult<T>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        result.setCount(count);
        return result;
    }

    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    /**
     * 自动计算分页信息
     * @param count
     * @param pageable
     */
    public void setPage(long count, Pageable pageable) {
        this.count = count;
        this.pageSize = pageable.getPageSize();
        this.currentPage = pageable.getPageNumber();
        this.totalPage = (long) Math.ceil((double)count / pageSize);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
