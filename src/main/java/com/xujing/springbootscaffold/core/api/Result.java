package com.xujing.springbootscaffold.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class Result<T> {

    // 时间戳
    private long timestamp = System.currentTimeMillis();

    // 消息
    private String message;

    // 返回码
    private ResultCodeEnum code = ResultCodeEnum.SUCCESS;

    // 数据
    private T data;

    public int getCode() {
        return code.getCode();
    }

    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <T> Result<T> success(T data, String message) {
        Result<T> result = new Result<>();
        result.setCode(ResultCodeEnum.SUCCESS);
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    /**
     * 无数据失败返回
     *
     * @param code    返回码
     * @param message 消息
     * @return ignore
     */
    public static Result failed(ResultCodeEnum code, String message) {
        Result<String> result = new Result<>();
        result.setCode(code);
        result.setData("");
        result.setMessage(message);

        return result;
    }

    public static <T> Result<T> failed(ResultCodeEnum code, T data, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setData(data);
        result.setMessage(message);

        return result;
    }
}