package com.rick.chap_01.bullet05_profiler.content02_gcprofiler;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @Author: Rick
 * @Date: 2022/10/30 22:43
 */
public class AlexClassLoader extends URLClassLoader {
    private final byte[] bytes;

    public AlexClassLoader(byte[] bytes) {
        super(new URL[0], ClassLoader.getSystemClassLoader());
        this.bytes = bytes;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return defineClass(name, bytes, 0, bytes.length);
    }
}
