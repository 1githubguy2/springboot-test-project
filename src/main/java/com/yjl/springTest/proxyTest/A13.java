package com.yjl.springTest.proxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/4/18 20:18
 */
public class A13 {
    interface Foo{
        void foo();
        int bar();
    }

    static class Target implements Foo{

        @Override
        public void foo() {
            System.out.println("target foo");
            myInner();
            System.out.println("myInner调用完毕");
        }

        @Override
        public int bar() {
            System.out.println("target bar");
            return 100;
        }

        public void myInner() {
            System.out.println("myInner");
        }
    }

    //自定义的InvocationHandler
//    interface InvocationHandler{
//        Object invoke(hgbhObject proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException;
//    }

    public static void main(String[] args) {
        Foo proxy = new $Proxy0(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
                //1.功能增强
                System.out.println("before....");
                //2.调用目标
//                new Target().foo();
                return method.invoke(new Target(), args);
            }
        });
        proxy.foo();
        proxy.bar();
    }
}
