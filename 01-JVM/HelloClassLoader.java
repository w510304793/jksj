package com.my.jk.JVM;

import lombok.SneakyThrows;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        HelloClassLoader classLoader = new HelloClassLoader();
        Class classLoaderClass = classLoader.findClass("Hello");
        Method method = classLoaderClass.getMethod("hello");
        method.invoke(classLoaderClass.newInstance());
    }

    @SneakyThrows
    @Override
    protected Class<?> findClass(String name) {
        Path path = Paths.get("/Users/wangxiaofei/Desktop/极客时间/Hello.xlass");
        byte[] b64 = Files.readAllBytes(path);
        for (int i = 0; i < b64.length; i++) {
            b64[i] = (byte) (255 - b64[i]);
        }
        return defineClass(name, b64, 0, b64.length);
    }
}
