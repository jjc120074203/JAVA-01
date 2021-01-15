package com.csizg.iot.portal.configuration;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**  
 * @Title:  HelloClassLoader.java   
 * @Description:    TODO
 * @author: chenjl     
 * @date:   2021年1月15日 下午3:11:01   
 * @version V1.0 
 * 
 */  
public class HelloClassLoader extends ClassLoader {
    public static void main(String[] args) throws Exception {
        Class<?> hello = new HelloClassLoader().findClass("Hello");
        Object object = hello.newInstance();
        Method method = hello.getMethod("hello");
        method.invoke(object);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
    	byte[] bytes = new byte[0];
        try {
        InputStream resourceAsStream = HelloClassLoader.class.getClassLoader().getResourceAsStream("Hello.xlass");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes1 = new byte[1024];
            int var5;
            while ((var5 = resourceAsStream.read(bytes1)) != -1) {
                byteArrayOutputStream.write(bytes1, 0, var5);
            }
            byteArrayOutputStream.close();
            bytes = byteArrayOutputStream.toByteArray();
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte)(255 - bytes[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.defineClass(name, bytes, 0, bytes.length);
    }
}
