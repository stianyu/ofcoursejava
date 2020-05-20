package my.learning.jdbc.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class AopFactory {

    //为obj对象提供AOP增强，通知类是adviser（通知对象） adviser:可能是事务管理器、日志管理器（AOP增强）、拦截器（AOP拦截）
    public static Object enableAop(Object obj, Object adviser) {
        Map<String, Method> adviserMethod = getAdviserMethod(adviser);
        Method beforeMethod = adviserMethod.get("before");
        Method afterMethod = adviserMethod.get("after");
        Method exceptionMethod = adviserMethod.get("exception");
        Method finalMethod = adviserMethod.get("final");

        Object proxyObj = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                try {
                    if (beforeMethod != null) {
                        beforeMethod.invoke(adviser);//前置通知
                    }
                    Object ret = method.invoke(obj, args);
                    if (afterMethod != null) {
                        afterMethod.invoke(adviser);//后置通知
                    }
                    return ret;
                } catch (Exception e) {
                    //System.err.println("发生异常：" + e.toString());
                    e.getCause().printStackTrace();
                    if (exceptionMethod != null) {
                        exceptionMethod.invoke(adviser);
                    }
                    return null;
                } finally {
                    if (finalMethod != null) {
                        finalMethod.invoke(adviser);
                    }
                }
            }
        });
        return proxyObj;
    }

    private static Map<String, Method> getAdviserMethod(Object adviser) {
        Method[] methods = adviser.getClass().getMethods();
        Map<String, Method> methodMap = new HashMap<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeAspect.class)) {
                methodMap.put("before", method);
            } else if (method.isAnnotationPresent(AfterAspect.class)) {
                methodMap.put("after", method);
            } else if (method.isAnnotationPresent(ExceptionAspect.class)) {
                methodMap.put("exception", method);
            } else if (method.isAnnotationPresent(FinalAspect.class)) {
                methodMap.put("final", method);
            }
        }
        return methodMap;
    }
}
