package com.yingglish.enumtest.pizza;

import com.yingglish.enumtest.pizza.Pizza;

/**
 * 通常策略模式由不同类实现同一个接口来实现的
 * 使用枚举实现策略模式
 */
public enum  PizzaDeliveryStrategy {
    EXPRESS {
        @Override
        public void deliver(Pizza pz) {
            System.out.println("Pizza will be delivered in express mode");
        }
    },
    NORMAL {
        @Override
        public void deliver(Pizza pz) {
            System.out.println("Pizza will be delivered in normal mode");
        }
    };

    public abstract void deliver(Pizza p);
}
