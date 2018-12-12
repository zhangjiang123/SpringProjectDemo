package com.itheima.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BeanFactory {

    private static Properties pro;

    private static Map<String,Object> beans;

    static{


        try {
            pro=new Properties();

            InputStream rs = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            pro.load(rs);

            beans =new HashMap<String, Object>();

            Enumeration  keys = pro.keys();

            while (keys.hasMoreElements()){

                String key = keys.nextElement().toString();
                String beanPath = pro.getProperty(key);

                Object value = Class.forName(beanPath).newInstance();

                beans.put(key,value);
            }

        } catch (IOException e) {
            throw new  ExceptionInInitializerError("初始化properties失败");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据bean 的名称获取对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName){
        return beans.get(beanName);

    }

/*    public static Object getBean(String beanName){
        Object bean=null;


        try {
            String beanPath =pro.getProperty(beanName);
//            System.out.println(beanPath);
            bean =Class.forName(beanPath).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return bean;

    }*/



}
