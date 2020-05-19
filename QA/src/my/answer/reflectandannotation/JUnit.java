package my.answer.reflectandannotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JUnit {
    /**
     * 每个@Test修饰的方法执行完成后，在控制台打印该方法是否通过了测试
     * @param cls： 类字节码对象
     */
    public static void run(Class<?> cls) {
        int numPass = 0;
        Method[] methods = cls.getMethods();
        System.out.println("-----------------开始测试---------------------");

        Method methodBefore = null;
        Method methodAfter = null;
        List<Method> methodTest = new ArrayList<>();
        for (Method method: methods) {
            if (method.isAnnotationPresent(Before.class)) {
                methodBefore = method;
            }
            if (method.isAnnotationPresent(After.class)) {
                methodAfter = method;
            }
            if (method.isAnnotationPresent(Test.class)) {
                methodTest.add(method);
            }
        }

        Object obj = null;
        try {
            obj = cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Method method: methodTest) {
            try {
                if (method.isAnnotationPresent(Test.class)) {
                    methodBefore.invoke(obj);
                    method.invoke(obj);
                    System.out.println('[' + cls.getName() + '.' + method.getName() + "]: 通过");
                    numPass++;
                }
            } catch (Exception e) {
                System.out.println('[' + cls.getName() + '.' + method.getName() + "]: 不通过");
                System.out.println("异常的名称：" + e.getCause().getClass().getSimpleName());
                System.out.println("异常的原因：" + e.getCause().getMessage());
            } finally {
                try {
                    methodAfter.invoke(obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println("-----------------测试结束---------------------");
        System.out.println("共有" + numPass + "个方法通过测试");
    }

    public static void main(String[] args) {
        JUnit.run(A.class);
    }
}
