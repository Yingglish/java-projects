package com.yingglish.innerclass;

/*
如果接口的实现类（或者父类的子类）只需要使用唯一的一次
那么该种情况下可【省略该类的定义】，改为使用【匿名内部类】

匿名内部类是不能加访问修饰符的

匿名内部类的定义
接口名称 对象名 = new 接口名称() {
    // 覆盖重写所有抽象方法
}
 */
public class InterfaceApplication {

    public static void main(String[] args) {
/*      MyInterface obj = new MyInterfaceImpl(); // 多态写法
        obj.method();*/

        // 使用匿名内部类 省略MyInterfaceImpl的定义
/*        MyInterface obj = new MyInterface() {
            @Override
            public void method() {
                System.out.println("实现类覆盖重写了方法");
            }

            @Override
            public String getArgs() {
                return null;
            }
        };*/

//        new InterfaceApplication().getMyInterface("arg1").method();
        System.out.println(new InterfaceApplication().getMyInterface("arg1").getArgs());
    }

    // 当所在的方法的形参需要被内部类里面使用时，该形参必须为final jdk8
    public MyInterface getMyInterface(/*final*/ String name) {
        return  new MyInterface() {
            @Override
            public void method() {
                System.out.println("形参要被final修饰");
            }

            @Override
            public String getArgs() {
//                name = "hhh"; // 报错 自动被修饰为final
                return name;
            }
        };
    }
}

interface MyInterface {
    void method();
    String getArgs();
}

class MyInterfaceImpl implements MyInterface {

    @Override
    public void method() {
        System.out.println("实现类覆盖重写了方法");
    }

    @Override
    public String getArgs() {
        return null;
    }
}