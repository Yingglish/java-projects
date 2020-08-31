package com.yingglish.io.filetest;

import java.io.File;

/**
 * File是IO流的基础
 */
public class FileTest {
    public static void main(String[] args) {
        // 分隔符可以使用"/"和"\",但是由于转义问题使用"\"时必须写"\\"
        String fileName = "D:" + File.separator + "Download";

        // File类是IO包中唯一代表磁盘文件本身的对象, 没有无参构造方法
        File file = new File(fileName);

        // 判断路径指向的文件/文件夹是否存在、是否目录
        if(file.exists() && file.isDirectory()){
            System.out.println("file是一个文件夹\n");

            // 获取目录下的所有文件/文件夹（仅该层路径下）
            File[] files = file.listFiles();
            System.out.print("路径下有文件：");
            assert files != null;
            for (File f : files){
                System.out.print(f + "\t");
            }
            System.out.println();

            System.out.println("files[0]的文件名：" + files[0].getName()); // 获取文件名、文件夹名
            System.out.println("files[0]的文件路径：" + files[0].getPath()); // 获取文件、文件夹路径
            System.out.println("files[0]的绝对路径：" + files[0].getAbsolutePath()); // 获取文件、文件夹绝对路径
            System.out.println("files[0]的父文件夹名：" + files[0].getParent()); // 获取文件父目录路径
            System.out.println(files[0].exists() ? "files[0]的存在" : "files[0]的不存在"); // 判断文件、文件夹是否存在
            System.out.println(files[0].canWrite() ? "files[0]的可写" : "files[0]的不可写"); // 判断文件是否可写
            System.out.println(files[0].canRead() ? "files[0]的可读" : "files[0]的不可读"); // 判断文件是否可读
            System.out.println(files[0].canExecute() ? "file[0]可执行" : "file[0]不可执行"); // 判断文件是否可执行
            System.out.println(files[0].isDirectory() ? "files[0]的是目录" : "files[0]的不是目录"); // 判断文件、文件夹是不是目录
            System.out.println(files[0].isFile() ? "files[0]的是文件" : "files[0]的不是文件"); // 判断拿文件、文件夹是不是标准文件
            System.out.println(files[0].isAbsolute() ? "files[0]的路径名是绝对路径" : "files[0]的路径名不是绝对路径"); // 判断路径名是不是绝对路径
            System.out.println("files[0]的最后修改时间：" + files[0].lastModified()); // 获取文件、文件夹上一次修改时间
            System.out.println("files[0]的大小：" + files[0].length() + " Bytes"); // 获取文件的字节数，如果是一个文件夹则这个值为0
            System.out.println("files[0]的路径转换为URI：" + files[0].toURI()); // 获取文件路径URI后的路径名 file:/D:/Download/文件名

            if (files[0].exists()){
                // 删除的如果是一个文件夹的话，文件夹下还有文件/文件夹，是无法删除成功的
                files[0].delete(); // 删除指定的文件、文件夹
            }
            if (files[1].exists()){
                files[1].deleteOnExit(); // 当虚拟机终止时删除指定的文件、文件夹
            }

        }


        String curDir = System.getProperty("user.dir"); // E:\project\basic-code
        System.out.println("你当前的工作目录为 :" + curDir);
    }
}
