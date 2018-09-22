package cn.bitflash.utils;

public class RedisDetail {

    //token缓存key
    public static final String REDIS_TOKEN = "token_";

    //token过期时间
    public static final Long REDIS_TOKEN_EXPIRED_TIME = 60 * 60 * 24 * 15L;
}
