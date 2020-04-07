package com.yingglish.io;

import java.io.*;

/**
 *	转换流：（简单的理解为包装流，就是将字节流包装以下，方便操作文本相关的文件）
 *		使用场景：
 *			别人给提供了字节流，而需要处理文本文件，这时候，就需要用转换流转换一下，更方便处理文本文件
 *
 *		作用：就是一字符流的方式读取或写出文本相关的数据
 *
 *		InputStreamReader：将字节输入流包装一下,让其更适合读取文本文件
 *			构造方法：
 *			1.InputStreamReader(InputStream in) 创建一个使用默认字符集的InputStreamReader。
 *			2.InputStreamReader(InputStream in, String charsetName)
 创建一个使用指定字符集的InputStreamReader。
 *			普通方法：
 *				public int read(char[] cbuf)
 *				int read(char[] cbuf, int offset, int length)

 *		OutputStreamWriter：将字节输出流包装一下,让其更适合写出文本文件
 *			构造方法：
 *			1. OutputStreamWriter(OutputStream out) 创建一个使用默认字符编码的OutputStreamWriter。
 *			2. OutputStreamWriter(OutputStream out, String charsetName)
 创建一个使用指定字符集的OutputStreamWriter。
 普通方法：
 void write(char[] cbuf, int off, int len)
 append(CharSequence csq,int start,int end)

 只有转换流可以设置字符集

 */
public class ChangeIo {

    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("oop" + File.separator +"MyEclipse快捷键.txt");
            fos = new FileOutputStream("MyEclipse快捷键-copy.txt");
            copyFile(fis,fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeIo(fis,fos);
        }
    }

    /**
     * 需求：别人给我们提供了一个字节输入流和字节输出流，我们却要复制文本文件。
     * 将字节流转包装为转换流
     */
    public static void copyFile(InputStream is,OutputStream os) {
		/*
		 * 构造方法：
 *			1.InputStreamReader(InputStream in) 创建一个使用默认字符集的InputStreamReader。
 *			2.InputStreamReader(InputStream in, String charsetName)
						创建一个使用命名字符集的InputStreamReader。

			构造方法：
 *			1. OutputStreamWriter(OutputStream out) 创建一个使用默认字符编码的OutputStreamWriter。
 *			2. OutputStreamWriter(OutputStream out, String charsetName)
					创建一个使用命名字符集的OutputStreamWriter。
		 */
        if (is == null || os == null) {
            throw new IllegalArgumentException("参数不能为null");
        }
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            isr = new InputStreamReader(is);
            osw = new OutputStreamWriter(os);

            char[] c = new char[1024];//这里不是1k	每次读取的数据装到该char数组
            int read = -1;//每一次真正读取的字符个数
            //复制文件，应该一边读取，一边直接写出
            while ((read = isr.read(c)) != -1) {
                //将读取char数组中的数据，写出去。读了多少个字符就写出多少个字符
                osw.write(c, 0, read);
            }
            osw.flush();//刷新，将内存中的数据写出到硬盘
            System.out.println("复制成功！");
        } catch (FileNotFoundException e) {
            System.out.println("文件未找到！");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("复制失败！");
            e.printStackTrace();
        } finally {
            //必须是先开后关，为了保证关流成功性，将后面关闭的流写在前面关流的finally中且，前面关流先try再if判断是否非null
            //因为关流是每一个流操作必须的，所有将关流抽取为一个方法
            IOUtil.closeIo(isr,osw);//这里只需要关闭这个转换流即可，因为转换流中包装了字节流，关闭的时候，会自动关闭字节流
        }
    }

}
