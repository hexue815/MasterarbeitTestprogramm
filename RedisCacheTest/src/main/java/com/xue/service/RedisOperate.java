package com.xue.service;

import com.xue.pojo.User;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.util.Map;

public interface RedisOperate {
    public String get(String key);

    public void set(String key, String value);

    public Boolean hasKey(String key);


}
