package com.yingglish.basic;

public class IntegerTest {
    public static void main(String[] args) {
        /**
         *  Integer、Short、Byte、Character、Long 的 valueOf 方法实现类似,Double 和 Float 比较特殊，每次返回新包装对象
         * 在 Java 8 中，Integer 缓存池的大小默认为 -128~127
         */
        Integer i = 100; // 装箱阶段自动替换为了 valueOf 方法，拆箱阶段自动替换为了 xxxValue 方法

        Integer j = 100;  // 相当于  Integer.valueOf(100);
        System.out.println(i == j); // 都是调用缓存池中的数值，是同一个对象，所以是相等

        /**
         * new Integer(100) 与 Integer.valueOf(100) 的区别在于：
         *
         * 1. new Integer(100) 每次都会新建一个对象；
         * 2. java在编译Integer i = 100 ;时，会翻译成为Integer i = Integer.valueOf(100)；
         *
         */

        Integer x = new Integer(100);
        Integer y = new Integer(100);
        System.out.println(x == y); // false


        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        System.out.println((a + b) == c); // true
        //  == 比较的是数值（自动触发拆箱过程），对于包装类型 equals 方法不会进行类型转换
        System.out.println((a + b) == g); // true
        System.out.println(g.equals(a+b)); // false


        int i1 = Integer.parseInt("11"); // 返回的是 int 型变量
        Integer i2 = Integer.valueOf("11"); //  返回的是 Integer 对象
        // intValue()是java.lang.Number类的方法把Integer对象类型变成int的基础数据类型
        // 自动拆箱和强制类型转换调用此方法
        int i3 = i2.intValue();
    }
}
