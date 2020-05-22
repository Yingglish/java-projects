package com.yingglish.innerclass;

/**
 * 为什么局部变量需要final修饰呢
 * 原因是：因为局部变量和匿名内部类的生命周期不同。
 * 匿名内部类是创建后是存储在堆中的，而方法中的局部变量是存储在Java栈中，当方法执行完毕后，就进行退栈，同时局部变量也会消失。那么此时匿名内部类还有可能在堆中存储着，那么匿名内部类要到哪里去找这个局部变量呢？
 * 为了解决这个问题编译器为自动地帮我们在匿名内部类中创建了一个局部变量的备份，也就是说即使方法执结束，匿名内部类中还有一个备份，自然就不怕找不到了。
 * 但是问题又来了。如果局部变量中的a不停的在变化。那么岂不是也要让备份的a变量无时无刻的变化。为了保持局部变量与匿名内部类中备份域保持一致。编译器不得不规定死这些局部域必须是常量，一旦赋值不能再发生变化了。所以为什么匿名内部类应用外部方法的域必须是常量域的原因所在了。
 *
 * 特别注意：在Java8中已经去掉要对final的修饰限制，但其实只要在匿名内部类使用了，该变量还是会自动变为final类型（只能使用，不能赋值）。
 */
public class Outer {
     int num =10; // 外部类成员变量

    public class Inner /* extends Object */{
        int num = 20; // 内部类成员变量

		/*
			当内部类和外部类实例变量相同时，内部类想调用外部类的实例变量，需要（外部类名.this.属性）
		*/
        public  void methodInner() {
            int num = 30; // 内部类方法的局部变量
            System.out.println(num);  // 就近原则
            System.out.println(this.num);
            System.out.println(Outer.this.num); // 访问外部类
        }
    }

    /*
    如果一个类定义在方法中, 成为局部内部类
    修饰符 class 外部类名称 {
        修饰符 返回值类型 外部方法名称(参数列表) {
            class 局部类名称 {
                // ...
            }
        }
        // ...
    }
     */
    public  void methodOuter() {

        // 所在方法局部变量
        final int n = 5; // java 8+ 开始 局部变量一直不变，final可省略

        class Inner {  // 局部内部类 只能在这个方法内使用这个类
            int num = 40;
            public void methodInner() {

                // 局部内部类，如果希望访问所在方法内的局部变量，那么这个变量必须是【有效final的】
                // 原因： new出来的对象在堆内存中，而局部变量是跟着方法走的，在栈内存中，方法运行之后立刻出栈，局部变量消失但new 出来的对象在堆中会持续存在，知道垃圾回收
                System.out.println(n);
                System.out.println(num); // 40
            }
        }

        Inner inner = new Inner();
        inner.methodInner();
    }
}
