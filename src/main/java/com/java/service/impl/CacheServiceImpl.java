package com.java.service.impl;

import com.java.service.CacheService;
import com.java.util.cache.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author:zhumeng
 * @desc:
 **/
@Service
public class CacheServiceImpl implements CacheService {
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Override
    public void removeFromCache(String keyPrefix) {
        Set<String> keySet=jedisKeys.keys(keyPrefix+"*");
        for (String key:keySet)
            jedisKeys.del(key);

    }
}
