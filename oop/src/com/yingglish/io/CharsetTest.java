package com.yingglish.io;

/**
 *	关于乱码问题：
 *		产生的原因：
 *			1. 编码的字符集和解码的字符集不一致
 *				因为字符集不统一，即编码getBytes()和解码new String(b,0,read,"gbk")字符集不一致
 *
 *			2. 用字节流读取字符的时候出现
 因为字节数组太小，读取汉字的时候，数组长度不够，将一个汉字拆开了
 *			解决方案：
 *				1. 用统一的字符集 且用字符流读取和写出文本相关文件
 *				2. 将所有环境都设置为统一字符集
 *				3. 用一个大的字节数组（一般不用）
 */
public class CharsetTest {

    public static void main(String[] args) throws Exception {
        /*
         * 编码：将字符串转换为字节
         * 		1. byte[] getBytes()  根据默认字符集将当前字符串转换为字节数组
         * 		2. byte[] getBytes(String charsetName) 	UTF-8/GBK
         * 			按照指定的字符集将将当前字符串转换为字节数组
         *
         * 		3. byte[] getBytes(Charset charset)   不用
         *
         * 解码：将字节转换为字符
         * 		1.String(byte[] bytes, int offset, int length)
         * 			根据默认字符集将字节数组中从指定下标开始到指定长度结束的数据转换为字符串
         * 		2.String(byte[] bytes, int offset, int length, String charsetName)
         * 			根据指定字符集将字节数组中从指定下标开始到指定长度结束的数据转换为字符串
         *
         * 		不常用
         * 		3.String(byte[] bytes) 	根据默认字符集将字节数组转换为字符串
         * 		4.String(byte[] bytes, String charsetName)
         * 			根据默认字符集将字节数组转换为字符串
         *
         * 这里会有乱码问题：
         * 	产生的原因：
         * 		1、因为字符集不统一，即编码和解码new String(b,0,read,"gbk")字符集不一致
         * 		2、因为字节数组太小，读取汉字的时候，数组长度不够，将一个汉字拆开了
         * 解决：
         * 	1. 用字符流用统一的字符集（最常用）
         *
         * 		浏览器	：	 UTF-8
         * 		前台：
         * 			HTML	：UTF-8
         * 			CSS		：UTF-8
         * 			JS、JSP	：UTF-8
         *
         * 		后台：
         * 			java	：UTF-8
         *
         * 		数据库：
         * 			mysql、oracle、DB2	：UTF-8
         *
         * 		开发工具：UTF-8
         *
         * 	2. 用一个大的字节数组（一般不用）
         */

        //编码：byte[] getBytes(String charsetName) 	UTF-8/GBK	按照指定的字符集将将当前字符串转换为字节数组
        byte[] bytes = "约吧".getBytes("GBK");


        //解码：String(byte[] bytes, int offset, int length, String charsetName)
        //根据指定字符集将字节数组中从指定下标开始到指定长度结束的数据转换为字符串
        String str = new String(bytes,0,bytes.length,"GBK");
        System.out.println(str);
    }

}
