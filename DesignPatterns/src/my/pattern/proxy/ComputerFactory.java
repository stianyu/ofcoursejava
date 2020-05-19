package my.pattern.proxy;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式：代理工厂实现了代理工厂接口
 */
public class ComputerFactory implements BuyComputer{

    @Override
    public void buyComputer(double money) {
//        int a = 1 / 0;
        System.out.println("花了" + money + "钱从工厂买了电脑");
    }

    @Override
    public void afterService() {
        System.out.println("从工厂提供售后");
    }

    public static void main(String[] args) {
        // 不使用代理模式
        ComputerFactory cf = new ComputerFactory();
//        cf.buyComputer(5000);
//        cf.afterService();
        System.out.println("------------------");

        // 静态代理： ComputerShop 是静态代理的实现类
        ComputerShop cs = new ComputerShop(cf);
//        cs.buyComputer(5000);
//        cs.afterService();

        /**
         * 动态代理：不用实现写实现类，通用性更强，可以使用InvocationHandler拦截器对所有的想拦截方法进行拦截
         * 首先必须定义一个接口，还要有一个InvocationHandler(将实现接口的类的对象传递给它)处理类。
         * 再有一个工具类Proxy(习惯性将其称为代理类，因为调用他的newInstance()可以产生代理对象,其实他只是一个产生代理对象的工具类）。
         * 利用到InvocationHandler，拼接代理类源码，将其编译生成代理类的二进制码，利用加载器加载，并将其实例化产生代理对象，最后返回。
         */
        Object proxyShop = Proxy.newProxyInstance(cf.getClass().getClassLoader(), cf.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("buyComputer")) {
                    try {
                        Double money = (Double) args[0];
                        System.out.println("从工厂提货");
                        Object ret = method.invoke(cf, 0.9 * money);
                        File f = new File("flag.txt");
                        f.createNewFile();
                        System.out.println("送一台电脑包");
                        return ret;
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("提货失败");
                        return null;
                    } finally {
                        System.out.println("交易完成");
                    }
                } else if (method.getName().equals("afterService")) {
                    File f = new File("flag.txt");
                    if (f.exists()) {
                        System.out.println("已购买，有售后服务");
                        Object ret = method.invoke(cf, args);
                        f.delete();
                        return ret;
                    } else {
                        System.out.println("还没购买，购买后可以提供售后服务");
                        return null;
                    }
                } else {
                    return method.invoke(cf, args);
                }
            }
        });
        BuyComputer proxyShop2 = (BuyComputer) proxyShop;
        proxyShop2.buyComputer(5000);
        proxyShop2.afterService();
    }
}
