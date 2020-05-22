package com.yingglish.innerclass;

/*
成员内部类定义格式
修饰符 class 外部类名称 {
    修饰符 class 内部类名称 {
        // ...
    }
    // ...
}

注 ：内用外，随意访问；外用内，需要内部类对象
 */
public class Body { // 外部类

    public class Heart { // 成员内部类

        public void beat() {
            System.out.println("砰砰砰");
            System.out.println("我叫：" + name); // 访问外部类
        }

    }

    private String name; // 外部类私有成员

    // 外部类方法

    public void methodBody() {
        System.out.println("外部类方法");
        Heart heart = new Heart();
        heart.beat();
    }

    public static void main(String[] args) {
        // 间接方式：在外部类方法中，使用内部类；然后通过main()调用外部类方法
        Body body = new Body(); // 外部类对象
        body.methodBody();

        System.out.println("==========");
        // 直接方式 ： 外部类名称.内部类名称 对象名 = new 外部类.new 内部类名称();

        Body.Heart heart2 = new Body().new Heart();
        heart2.beat();
    }
}
