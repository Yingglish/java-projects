package com.yingglish.interfacedemo;


/*
1. 接口的默认方法，能够被接口实现类对象直接调用
2. 接口的默认方法，也可以被接口实现类进行覆盖重写
 */
public class MyInterfaceDefault  {
    public static void main(String[] args) {
        MyInterfaceImpl interfaceDemo = new MyInterfaceImpl();
        interfaceDemo.methods();
        interfaceDemo.defaultMethod(); // 调用默认方法，如果实现类中没有，会向上找接口
    }
}
