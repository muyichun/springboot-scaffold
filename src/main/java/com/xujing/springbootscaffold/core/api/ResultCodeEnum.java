package com.xujing.springbootscaffold.core.api;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCodeEnum {
    SUCCESS(200), // 成功
    FAIL(400), // 失败
    UNAUTHORIZED(401), // 未认证（签名错误）
    PERMISSION_ERROR(403),//没有访问权限
    NOT_FOUND(404), // 接口不存在
    INTERNAL_SERVER_ERROR(500), // 服务器内部错误
    IMPORT_ERROR(201);//导入的数据有错误
    private final int code;

    ResultCodeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
