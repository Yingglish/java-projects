package com.yingglish.interfacedemo;

/**
 * java8 开始允许定义静态方法
 * 格式：
 * public static 返回值类型 方法名称(参数列表) {
 *     方法体
 * }
 */
public class MyInterfaceStatic {
    public static void main(String[] args) {
        MyInterfaceImpl interfaceDemo = new MyInterfaceImpl();
//        interfaceDemo.staticMethod(); // 不能通过接口实现类调用接口中的静态方法
        MyInterface.staticMethod(); // 正确调用
    }
}
