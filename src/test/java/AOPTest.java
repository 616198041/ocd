import atguigu.impl.MyMathCalculator;
import atguigu.inter.Calculator;
import atguigu.proxy.CalculatorProxy;
import org.junit.Test;

import java.util.Arrays;

public class AOPTest {
/*
* 有了动态代理，日志记录可以做的非常强大；而且与业务逻辑解耦
*
* jdk默认的动态代理，如果目标对象没有实现任何接口，是无法为他创建代理对象的
*
* */

    @Test
    public void test(){
        Calculator calculator = new MyMathCalculator();
        calculator.add(2,3);
        calculator.div(2,1);

        System.out.println("===============");
        //通过代理对象对方法进行实行，代理对象可以额外在方法不同关注点进行操作
            Calculator proxy = CalculatorProxy.getProxy(calculator);
            //class com.sun.proxy.$Proxy4 也是实现了Calculator接口
        //代理对象和被代理对象，唯一能产生关联的就是实现了同一个接口
        System.out.println(Arrays.asList(proxy.getClass().getInterfaces()));
            proxy.add(2,1);
            proxy.sub(3,1);
//            proxy.mul(3,3);
//            proxy.div(2,2);
    }
}
