 package com.yingglish.io.filetest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
  *  从 Web 上读取数据
  *  eg:	输入一个URL地址: 
  * 		http://www.baidu.com
  * 		The file size is 2283 characters
  *
  */
public class ReadFileFromURL {
	public static void main(String[] args) {
		System.out.println("输入一个URL地址: ");
		String URLStr = new Scanner(System.in).next();
		try{
			URL url = new URL(URLStr);
			int count = 0;
			Scanner input = new Scanner(url.openStream());
			while (input.hasNext()) {
				String line = input.nextLine();
				count += line.length();
			}
			System.out.println("The file size is " + count + " characters");
		} catch (MalformedURLException e) {
			// TODO: handle exception
			System.out.println("非法的URL地址");
		} catch (IOException e) {
			System.out.println("I/O Errors: no such file");
		}
	}
}