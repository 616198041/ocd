package atguigu.proxy;

import atguigu.inter.Calculator;
import atguigu.utils.LogUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/*
*帮Calculator生成代理对象的类
*
* */
public class CalculatorProxy {
/*
* 为传入的参数对象创建一个动态代理对象
*Calculator calculator：被代理对象
* 返回代理对象
* */
    public static Calculator getProxy(final Calculator calculator){

        //InvocationHandler h:方法执行器，帮目标对象执行目标方法
        InvocationHandler h =new InvocationHandler() {
            /*InvocationHandler接口实现的三个参数
            * Object proxy：代理对象；给jdk使用，任何时候我们都不使用这个
            * Method method：当前将要执行的目标对象的目标方法
            * Object[] args：这个方法调用时外界的传入的参数值
            * */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Object result = null;
                try {
                    LogUtils.logStart(method,args);
                    // 利用反射执行目标方法
                    //参数：被代理对象，传入的参数
                    result = method.invoke(calculator, args);
                    LogUtils.logReturn(method, result);
                } catch (Exception e) {
                        LogUtils.logException(method,e);
                }finally {
                    LogUtils.logEnd(method);

                }


                //返回值：目标方法执行完后的返回值
                //返回值必须返回出去外界才能拿到真正执行后的返回值
                return result;
            }
        };
        Class<?>[] interfaces= calculator.getClass().getInterfaces();
         ClassLoader loader =calculator.getClass().getClassLoader();

         //Proxt为目标对象创建代理对象；
        //loader被代理对象的类加载器
        //interfaces被代理对象所实现的所有接口

        Object proxy = Proxy.newProxyInstance(loader, interfaces, h);
        return (Calculator) proxy;
    }
}
