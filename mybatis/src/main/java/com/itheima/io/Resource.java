package com.itheima.io;

import java.io.InputStream;

public class Resource {
    public static InputStream getResource(String Path){
        InputStream resourceAsStream = Resource.class.getClassLoader().getResourceAsStream(Path);
        return resourceAsStream;
    }
}
