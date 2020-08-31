package com.yingglish.io.filetest;

import java.io.File;
import java.util.Scanner;

/**
 *  文本I/O : 使用 Scanner 类读取文本数据，使用 PrintWriter 类写文本數据
 *
 */
public class ReadData {
	public static void main(String[] args) {
		File file = new File("score.txt");
		
		// 为文件创建一个Scanner
		try(Scanner input = new Scanner(file)) {
			/**
			 * next(),nextXxx()被标记读取方法，因为它们会读取用分隔符分隔开的标记
			 * 默认情况下，分隔符是空格
			 * 可以使用 useDelimiter(String regex)方法设置新的分隔符模式
			 */
			while (input.hasNext()) {
				String firstName = input.next();
				String mi = input.next();
				String lastName = input.next();
				int score = input.nextInt();
				System.out.println(firstName + " " + mi + " " + lastName +
						" " + score);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
