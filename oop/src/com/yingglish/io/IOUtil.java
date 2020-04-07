package com.yingglish.io;

import java.io.*;

/**
 *	IO工具类，有关流方法
 */
public class IOUtil {

    /**
     * 关闭字节流方法
     * @param is	所有输入字节流父类
     * @param os	所有输出字节流父类
     */
    public static void closeIo(InputStream is,OutputStream os) {
        try {
            if (os != null) {
                //1. 必须要关闭IO流，节约资源开销		2. 关闭IO流原则，先开后关
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {//关流必须在finally结构中
            try {
                if (is != null) {
                    //1. 必须要关闭IO流，节约资源开销		2. 关闭IO流原则，先开后关
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭字符流方法
     * @param reader	所有输入字符流父类
     * @param writer	所有输出字符流父类
     */
    public static void closeIo(Reader reader, Writer writer) {
        try {
            if (writer != null) {
                //1. 必须要关闭IO流，节约资源开销		2. 关闭IO流原则，先开后关
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {//关流必须在finally结构中
            try {
                if (reader != null) {
                    //1. 必须要关闭IO流，节约资源开销		2. 关闭IO流原则，先开后关
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
