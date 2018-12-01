package com.java.service;

/**
 * 依据key前缀删除匹配该模式下的所有key-value
 *
 * @author:zhumeng
 * @desc:
 **/
public interface CacheService {
    /**
     * 依据key前缀删除匹配该模式下的所有key-value
     *
     * @param keyPrefix
     */
    void removeFromCache(String keyPrefix);
}
