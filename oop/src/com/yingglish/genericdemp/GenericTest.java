package com.yingglish.genericdemp;

import java.util.Arrays;

/**
 *	泛型：
 *		1. 概念：就是一个占位符号（数据类型）。
 *				就是在设计类（接口等）的时候，没有给规定具体是什么类型的参数，
 *				在实例化或调用的时候再传入具体的类型，告诉编译器这是什么类型。
 *		2. 作用：
 *				1. 帮助程序员校验代码(用泛型约束容器中的元素类型)
 *				2. 提高程序的拓展性
 *				3. 避免强制转换：遍历Map
 *
 *		3. 使用：
 *				3.1 泛型类：
 *					语法：直接在类名后加上<数据类型>，一般情况下数据类型用字母代替
 *
 *				3.2 泛型方法: 可以规避new关键字创建对象
 *					语法：在修饰符后，返回值前加上<数据类型>
 *
 *				3.3 泛型的上限和下限：
 *						T super Number: 表示T最小类型取值到Number类型
 *						T extends Number: 表示T最大类型取值到Number类型
 *						<? super T>
 *
 *				3.4 泛型的嵌套:
 *					Set<Map.Entry<K,V>> entrySet() 返回所有键值对的Set集合。
 *
 *		4. 注意事项：
 *				1.一些特殊字母的含义：
 *					E element 元素
 *					T type	类型
 *					K key	键
 *					V value	值
 *					R result 返回值
 *				2. 在创建对象的时候，泛型使用，必须保证=两边完全一致对应
 *				3. 泛型必须是引用数据类型
 *
 *		5. 扩展：
 *				List<String> l1 = new ArrayList<String>();
 *              List<Integer> l2 = new ArrayList<Integer>();
 *
 *              System.out.println(l1.getClass() == l2.getClass()); // true
 *
 *              泛型信息只存在于代码编译阶段，在进入 JVM 之前，与泛型相关的信息会被擦除掉，专业术语叫做类型擦除
 *
 *              在泛型类被类型擦除的时候，之前泛型类中的类型参数部分如果没有指定上限，如 <T>则会被转译成普通的 Object 类型，如果指定了上限如 <T extends String>则类型参数就被替换成类型上限
 */
public class GenericTest<T> {

    public static void main(String[] args) {
        //测试下面方法
        Integer[] arr1 = {6,69,9};
        Integer[] arr2 = {3,33,13};
        Integer[] mergeAndSort = mergeAndSort(arr1,arr2);
        System.out.println(Arrays.toString(mergeAndSort));
    }

    /**
     * 泛型方法
     * 需求：设计一个方法，
     * 		1. 可以将两个相同数据类型的数组合并，并且升序排序,并且将合并排序后的数组返回
     * 		2. 只有能比较大小的，才能排序，所以只能传入数字相关的类型Number子类		泛型上限
     * 	问题：
     * 		1. 在静态区不能使用非静态成员，这时候就必须使用泛型方法解决该问题
     * 		2. new对象的时候，必须是确定的类型
     */
    public static<T extends Number> T[] mergeAndSort(T[] arr1, T[]arr2) {
        if (arr1 == null || arr2 == null) {
            throw new IllegalArgumentException("请注意参数不能为null！");
        }

        //创建新数组，新数组长度是两个数组之和
//		T[] newArr = new T[arr1.length + arr2.length];//new对象的时候，必须是确定的类型
        T[] newArr = Arrays.copyOf(arr1, arr1.length + arr2.length);//将arr1中的元素复制到newArr中

        //将arr2中的元素复制到newArr中
        System.arraycopy(arr2, 0, newArr, arr1.length, arr2.length);

        //排序
        Arrays.sort(newArr);
        return newArr;
    }
}
