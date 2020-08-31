package com.yingglish.genericdemo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型擦除可能带来的问题：
 *  1. 强制类型转化
 *  2. 引用传递问题
 */
public class GenericErasureDemo {
    public static void main(String[] args) {
        List list = new ArrayList();
        List<String> listString = new ArrayList<>();
        List<Integer> listInteger = new ArrayList<>();

        try {
            list.getClass().getMethod("add", Object.class).invoke(list, 1);
            listString.getClass().getMethod("add", Object.class).invoke(listString, 1);
            listInteger.getClass().getMethod("add", Object.class).invoke(listInteger, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("list size:" + list.size());
        System.out.println("listString size:" + listString.size());
        System.out.println("listInteger size:" + listInteger.size());
    }

    @Test
    public void test() {
        List<Integer> a = new ArrayList<>(); // 编译后为原生的ArrayList 泛型信息丢失
        List<String> b = new ArrayList<>();

        System.out.println(a.getClass()); // class java.util.ArrayList
        System.out.println(a.getClass() == b.getClass()); //结果true
    }
}
