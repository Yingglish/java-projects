package com.yingglish.interfacedemo;

/*
接口就是多个类的公共规范
接口是一种引用数据类型，最重要的内容是其中的抽象方法

定义：
public interface 接口名称 {
}

备注：换成关键字interface后，编译生成的字节码文件任然是 .java --> .class

接口中可包含的内容
jdk 1.7 -- 1. 常量 2. 抽象方法
jdk 1.8 -- 额外 3.默认方法 和 4.静态方法
jdk 9 -- 额外 5. 私有方法
 */

public class MyInterfaceImpl implements MyInterface {

    @Override
    public void methods() { // 实现接口内的抽象方法
        System.out.println("覆盖重写了接口内的抽象方法");
    }
}
