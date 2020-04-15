package com.yingglish.java8;

import com.yingglish.java8.ref.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream接口
 *
 * Stream与Collection集合区别
 *      Stream关注的是对数据的运算，与CPU打交道
 *      集合关注的是数据的存储，与内存打交道
 *
 * Stream特点
 *      1. Stream 自己不会存储元素
 *      2. Stream不会改变源对象。相反的，它们会返回一个包含结果的新Stream
 *      3. Stream 操作是延迟的。即它们会等到需要结果的时候才会执行
 *
 * Stream 执行流程
 *      1. Stream的实例化
 *      2. 一系列的中间操作(过滤、映射 ...)
 *      3. 终止操作
 *
 * 注意
 *      * 一个中间操作链，对数据源的数据进行处理
 *      * 一旦执行终止操作，就执行中间操作链，并产生结果。之后，这个Stream对象不能被使用
 *
 * Stream的实例化
 *
 * 顺序流 与 并行流的互转
 *      parallel()
 *          把此Stream流转成并行流
 *      sequential()
 *          把此Stream流转成顺序流
 *
 */
public class StreamApiTest {
    List<Employee> employeeList = Employee.getEmployees();

    /**
     * 方式1：通过集合的默认方法创建Stream对象
     */
    @Test
    public void test1() {
        // default Stream<E> stream(): 返回一个顺序流
        Stream<Employee> stream = employeeList.stream();
        System.out.println(stream);

        // default Stream<E> parallelStream(): 返回一个并行流
        Stream<Employee> parallelStream = employeeList.parallelStream();
        System.out.println(parallelStream);
    }

    /**
     * 方式2：通过数组Arrays类的默认方法创建Stream对象
     * 只适用于顺序流
     */
    @Test
    public void test2() {
        int[] arr = new int[]{3, 1, 4, 7};

        // 调用Arrays类的public static <T> Stream<T> stream(T[] array): 返回一个流
        IntStream intStream = Arrays.stream(arr);

        // 自定义数据类型的数组
        Employee e1 = new Employee(1001, "Xu sanduo");
        Employee e2 = new Employee(1002, "Liu shaoqi");
        Employee[] eArr = {e1, e2};
//        Employee[] eArr = new Employee[]{e1, e2};
        Stream<Employee> employeeStream = Arrays.stream(eArr);

        // Arrays类没有 Stream<T> parallelStream(T[] array) 方法
//        Stream<Employee> employeeStream = Arrays.parallelStream(eArr); // 错误

    }

    /**
     * 方式3：通过Stream接口的of()方法创建Stream对象
     *
     * public static<T> Stream<T> of(T t)
     */
    @Test
    public void test3() {
        Stream<Integer> stream = Stream.of(13, 13, 1, 3, 5);
    }

    /**
     * 方式4：创建无限流
     *
     * public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
     * public static<T> Stream<T> iterate(T seed, Predicate<? super T> hasNext, UnaryOperator<T> next)
     * public static<T> Stream<T> generate(Supplier<? extends T> s)
     */
    @Test
    public void test4() {
        // 打印 0 - 9
        Stream.iterate(0, n -> n + 1)
                .limit(10)
                .forEach(System.out::println);

        // 打印10个奇数
        Stream.iterate(0, n -> n + 1)
                .filter(x -> x % 2 != 0) //odd
                .limit(10)
                .forEach(System.out::println);

        // Fibonacci example
        Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
                .limit(20)
                .map(n -> n[0])
                .forEach(System.out::println);

        // 生成
        Stream<Double> stream1 = Stream.generate(Math::random);
//        stream1.forEach(System.out::println); // 不停遍历下去
        stream1.limit(5).forEach(System.out::println);

    }

    /**
     * 顺序流 与 并行流的互转
     *
     * parallel()
     *      把此Stream流转成并行流
     * sequential()
     *      把此Stream流转成顺序流
     */
    @Test
    public void test5() {
        Stream<Employee> parallel = employeeList.stream().parallel();
        Stream<Employee> sequential = employeeList.parallelStream().sequential();
    }

}
