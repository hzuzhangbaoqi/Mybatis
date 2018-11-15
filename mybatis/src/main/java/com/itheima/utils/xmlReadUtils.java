package com.itheima.utils;

import com.itheima.domain.Config;
import com.itheima.domain.Mapper;
import com.itheima.io.Resource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class xmlReadUtils {

    private InputStream inputStream;

    public xmlReadUtils() {
    }

    public xmlReadUtils(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    public Config xmlReadConfig() throws DocumentException {
        Config cfg = new Config();
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);

        //获取根节点
        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//property");
        for (Element property : list) {
            String name = property.attributeValue("name");
            switch (name) {
                case "username":cfg.setUsername(property.attributeValue("value"));
                    break;
                case "password":cfg.setPassword(property.attributeValue("value"));
                    break;
                case "driver": cfg.setDriver(property.attributeValue("value"));
                    break;
                case "url":cfg.setUrl(property.attributeValue("value"));
                    break;
            }
        }

        List<Element> mappersNode = rootElement.selectNodes("//mapper");

        for (Element element : mappersNode) {
            String name = element.attributeValue("resource");
            if(name != null){
                System.out.println("这里使用配置文件");
                InputStream resource = Resource.getResource(name);
                Map<String, Mapper> mapperMap = loadHandleMapper(resource);
                cfg.setMap(mapperMap);
                return cfg;
            }else{
                System.out.println("这里使用注解");
            }
        }

        return null;
    }

    private Map<String, Mapper> loadHandleMapper(InputStream resource) throws DocumentException {
        Map<String,Mapper> map = new HashMap<String,Mapper>();
        SAXReader reader = new SAXReader();
        Document document = reader.read(resource);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        List<Element> elements = rootElement.selectNodes("//select");
        for (Element element : elements) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");

            String sql = element.getTextTrim();

            Mapper m = new Mapper();
            m.setSqlQuery(sql);
            m.setResultType(resultType);

            String key = namespace + "." + id;
            System.out.println(key);
            map.put(key,m);
        }
        return map;
    }
}
