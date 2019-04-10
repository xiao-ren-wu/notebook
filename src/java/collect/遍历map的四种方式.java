package com.xrw.collecton;

import java.util.*;

/**
 * @Created with IDEA
 * @author:Yuwenbo
 * @Date:2018/3/27
 * @Time:9:53
 * @JDK versions: 1.8.0_101
 * @apiNote 遍历map的四种方式
 */
public class printMap {
    public static void main(String[] args) {
        HashMap<String, String> hashmap = new HashMap<>();
        hashmap.put("key1","1");
        hashmap.put("key2","2");
        hashmap.put("key3","3");
        hashmap.put("key4","4");
        System.out.println(hashmap);
        method4(hashmap);
    }

    /**
     * 方法1：通过keySet方法获取键的set集合，遍历set集合并将key传给get方法得到value
     * @param hashmap
     */
    private static void method1(HashMap<String,String> hashmap){
        for(String key:hashmap.keySet()){
            System.out.println(key+"::"+hashmap.get(key));
        }
    }

    /**
     * 通过entrySet方法获取set类类型包含key和value的所有对象，遍历set集合，通过get方法获取key和value
     * @param hashMap
     */
    private static void method2(HashMap<String,String> hashMap){
        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
        for(Map.Entry<String, String> set:entries){
            System.out.println(set.getKey()+"::"+set.getValue());
        }
    }

    /**
     * 通过entrySet方法获取包含key和value的set集合，得到set的迭代器，获取entry对象，通过get方法获取key和value
     * @param hashMap
     */
    private static void method3(HashMap<String,String> hashMap){
        Iterator<Map.Entry<String, String>> iterator = hashMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String> entry = iterator.next();
            System.out.println(entry.getKey()+"--"+entry.getValue());
        }
    }

    /**
     * 通过values方法获包含value的set集合，遍历集合得到value
     * @param hashMap
     */
    private static void method4(HashMap<String,String> hashMap){
        Collection<String> values = hashMap.values();
        Iterator<String> iterator = values.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}