package com.xujing.springbootscaffold.core.common;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;


/**
 * @description 登录校验Token
 */
@Slf4j
public class TokenUtil {

    /**
     * 校验token不同登录用户（会员、用户、中台、H5全科医生）
     */
    public static final int MEMBER = 11; // 会员
    public static final int SYSTEM_USER = 12; // 系统用户
    public static final int GENERAL_DOCTOR = 13; // H5端全科医生登陆
    public static final int COMPANY_HR = 14; // 企业HR
    public static final int SPECIALIST = 15; //医生端


    /**
     * 密钥
     */
    private static String TOKEN_CODE = "guo@)$kang402";

    /**
     * token过期时间,单位是秒,默认30天
     */
    private static final long TOKEN_EXPIRE_TIME = 24 * 60 * 60 * 30;

    /**
     * 校验成功
     */
    public static int VERIFY_SUCCESS = 0;

    /**
     * 校验不匹配
     */
    public static int VERIFY_NOT_MATCH = 1;

    /**
     * 登录过期
     */
    public static int VERIFY_LOGON_TIMEOUT = 2;


    /**
     * 创建token(这里的id可能是会员id/公司认证码，通过typr区分用户还是会员还是企业HR)
     */
    public static String createToken(String id, int type) {
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        String md5 = Md5Util.md5s32(time + TOKEN_CODE);
        String typeStr = String.valueOf(type);
        return md5 + time + typeStr + id;
    }


    /**
     * 校验Token信息
     */
    public static TokenParsedResult parseTokenStr(String token) {
        TokenParsedResult result = new TokenParsedResult();
        if (token == null) {
            log.info("token is null");
            result.setParseResult(VERIFY_NOT_MATCH);
        } else {
            int length = token.length();
            if (length < 44) {
                log.error(StrUtil.format("token's length error:{}",token));
                result.setParseResult(VERIFY_NOT_MATCH);
            } else {
                try {
                    String md5 = token.substring(0, 32);
                    String timeS = token.substring(32, 42);
                    // 用于校验不同登录角色信息（会员、系统用户、H5端全科医生登录、企业HR）
                    String type = token.substring(42, 44);
                    String id = token.substring(44);
                    long time = Long.parseLong(timeS);
                    if (System.currentTimeMillis() / 1000 - time > TOKEN_EXPIRE_TIME) {
                        log.error(StrUtil.format(
                                "token already timeout: systemtime={},tokentime={},token={}",
                                System.currentTimeMillis() / 1000,time,token));
                        result.setParseResult(VERIFY_LOGON_TIMEOUT);
                    } else {
                        if (Md5Util.md5s32(timeS + TOKEN_CODE).equals(md5)) {
                            result.setParseResult(VERIFY_SUCCESS);
                            result.setId(id);
                            result.setType(type);
                        } else {
                            log.error(
                                    StrUtil.format("token not match error:{}", token));
                            result.setParseResult(VERIFY_NOT_MATCH);
                        }
                    }
                } catch (Exception e) {
                    log.error(
                            StrUtil.format("we met exception in analyzing token,token={}",
                                    token), e);
                    result.setParseResult(VERIFY_NOT_MATCH);
                }
            }
        }
        return result;
    }

    /**
     *
     * @author Emrys
     * @description Token解析结果
     * @since 2013年9月14日 下午2:07:51
     */
    public static class TokenParsedResult {

        /**
         * 解析结果
         */
        private int parseResult;
        /**
         * 用户id
         */
        private String Id;

        /**
         * 用户类型 用于校验不同登录角色信息（会员、系统用户、H5端全科医生登录、企业HR）
         */
        private String type;

        public int getParseResult() {
            return parseResult;
        }

        public void setParseResult(int parseResult) {
            this.parseResult = parseResult;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getType() {

            return type;
        }

        public void setType(String type) {

            this.type = type;
        }
    }


}
