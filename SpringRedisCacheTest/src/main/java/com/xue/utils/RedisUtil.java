package com.xue.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.*;
import java.util.concurrent.Callable;

public class RedisUtil implements Cache {
    private RedisTemplate<String, Object> redisTemplate;
    private String name;

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.redisTemplate;
    }


    /**
     * 从缓存中获取key
     */
//    @Override
//    public ValueWrapper get(Object key) {
//        System.out.println("-------------------->get key from Redis");
//        final String keyf = key.toString();
//        Object object = null;
//        object = redisTemplate.execute((RedisCallback<Object>) connection -> {
//            byte[] key1 = keyf.getBytes();
//            byte[] value = connection.get(key1);
//            if (value == null) {
//                return null;
//            }
//            return toObject(value);
//        });
//        return (object != null ? new SimpleValueWrapper(object) : null);
//    }
    @Override
    public ValueWrapper get(Object key) {
        System.out.println("-------------------->get key from Redis----------------");
        final String keyf = key.toString();
        Object object = null;
        object = redisTemplate.execute((RedisCallback<Object>) connection -> {
            byte[] key1 = keyf.getBytes();
            byte[] value = connection.get(key1);
            if (value == null) {
                return null;
            }
            return toObject(value);
        });
        return (object != null ? new SimpleValueWrapper(object) : null);
    }


    /**
     * 将一个新的key保存到缓存中 先拿到需要缓存key名称和对象，然后将其转成ByteArray
     */
    @Override
    public void put(Object key, Object value) {
        System.out.println("-------------------->put key in Redis----------------");
        final String keyf = key.toString();
        final Object valuef = value;
        System.out.println(valuef.getClass().toString());
        System.out.println(valuef);
        redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] keyb = keyf.getBytes();
            byte[] valueb = toByteArray(valuef);
            System.out.println(valueb);
            connection.set(keyb, valueb);
            return 1L;
        });
    }

//    @Override
//    public void put(Object key, Object value) {
//        System.out.println("-------------------->put key in Redis");
//        final String keyf = key.toString();
//        final Object valuef = value;
//        redisTemplate.execute(new RedisCallback<Long>() {
//            public Long doInRedis(RedisConnection connection) throws DataAccessException {
//                byte[] keyb = keyf.getBytes();
//                byte[] valueb = toByteArray(valuef);
//                connection.set(keyb, valueb);
//                return 1L;
//            }
//        });
//    }

    private byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    private Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    /**
     * 删除key
     */
    @Override
    public void evict(Object key) {
        System.out.println("del key");
        final String keyf = key.toString();
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(keyf.getBytes());
            }
        });
    }

    /**
     * 清空key
     */
    @Override
    public void clear() {
        System.out.println("clear key");
        redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return null;
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return null;
    }

    @Override
    public <T> T get(Object arg0, Callable<T> arg1) {
        // TODO Auto-generated method stub
        return null;
    }
}
