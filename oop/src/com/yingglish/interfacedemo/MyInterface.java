package com.yingglish.interfacedemo;

/**
 * 接口中的抽象方法，修饰符必须是俩个固定的关键子：public abstract(这两个关键字修饰符可选择性省略)
 * 接口中的静态变量 默认使用 public static final 修饰
 */
public interface MyInterface {

    // final 关键字 -->  不可变
    public static final int num = 10; // 常量定义，一旦赋值不可更改

    public abstract void methods(); //这是一个抽象方法

//    public abstract void newMethod(); //新增一个抽象方法

/*     java 8 开始接口可定义默认方法 : public default 返回值类型 方法名称(参数列表) { // 方法体 }
     默认方法可解决接口升级问题 --> 新增抽象方法*/

//    新增一个抽象方法 ，改成默认方法
    public default void defaultMethod(){
//        staticMethod();  // 可 调用静态方法
        System.out.println("新添加的默认方法");
    }

    // 接口静态方法
    public static void staticMethod() {
        System.out.println("这是一个静态方法");
    }

//    private void privateMethod() { // java9
//
//    }
}
