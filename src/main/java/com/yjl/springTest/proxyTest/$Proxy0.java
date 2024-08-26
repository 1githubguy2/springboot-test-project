package com.yjl.springTest.proxyTest;

import com.yjl.springTest.proxyTest.A13.Foo;
import com.yjl.springTest.proxyTest.A13.Target;
//import com.yjl.springTest.proxyTest.A13.InvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/4/18 20:21
 */
public class $Proxy0 extends Proxy implements Foo {
    //为了不让代理的方法实现不固定死，这里需要设计一个接口来进行回调具体的方法实现
//    private InvocationHandler h;
    public $Proxy0(InvocationHandler h) {
        super(h);
    }

    static Method foo;
    static Method bar;
    //避免代理对象每次调用方法时都创建Method对象，这里在静态代码块中做初始化即可
    static {
        try {
            foo = Foo.class.getMethod("foo");
            bar = Foo.class.getMethod("bar");
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }

    @Override
    public void foo() {
//        //1.功能增强
//        System.out.println("before....");
//        //2.调用目标
//        new Target().foo();
        try {
//            Method foo = Foo.class.getMethod("foo");
            h.invoke(this, foo, new Object[0]);
        } catch (RuntimeException | Error e) {//运行时异常，直接抛出
            throw e;
        } catch (Throwable e) {//检查异常，转化后抛出
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public int bar() {
        try {
//            Method bar = Foo.class.getMethod("bar");
            Object result = h.invoke(this, bar, new Object[0]);
            return (int) result;
            //需要知道代理对象执行方法时是否出现异常，所以需要抛出异常
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }
}
