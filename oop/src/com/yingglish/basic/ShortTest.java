package com.yingglish.basic;

/**
 * short 数据类型是 16 位、有符号的以二进制补码表示的整数
 * 最小值是 -32768 (-2^15) 最大值是 32767 (2^15 - 1)
 *
 * 默认值是 0
 */
public class ShortTest {
    public static void main(String[] args) {
        // 在Java中，常数默认是int类型的，所以一个常数不能超过Integer.MAX_VALUE 2147483647
       //  short i = 32768; // 编译出错，超过范围
        short i1 = 3 * 5;
//         short i2 = i1 + 5; // short + int 转为int > short
        int i2 = i1 + 5;
        short i3 = 3 + 5;
//        i3 = i2 + i3; // 编译错误 int + short +运算符类型必须一致
        i3 += i2; // += 运算符既可以实现运算，又不会更改 i3 的数据类型
//        i3 = i2 * i3; // 编译错误
        i3 *= i2;

        System.out.println(i3);
    }
}
