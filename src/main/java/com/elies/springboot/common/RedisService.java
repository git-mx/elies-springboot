package com.elies.springboot.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author 牟雪
 * @since 2018/4/16
 */
@Service
public class RedisService {

    private Logger logger = LoggerFactory.getLogger(RedisService.class);


    @Autowired
    private JedisPool jedisPool;

    public Jedis getResource() {
        return jedisPool.getResource();
    }

    @SuppressWarnings("deprecation")
    public void returnResource(Jedis jedis) {
        if(jedis != null){
            jedisPool.returnResourceObject(jedis);
        }
    }

    public void set(String key, String value) {
        Jedis jedis=null;
        try{
            jedis = getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            returnResource(jedis);
        }
    }

    public String get(String key) {
        String result = null;
        Jedis jedis=null;
        try{
            jedis = getResource();
            result = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            returnResource(jedis);
        }
        return result;
    }

    public boolean expire(String key, Integer expireSeconds){
        try{
            Jedis jedis = getResource();
            jedis.expire(key, expireSeconds);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return true;
        }
    }
}
