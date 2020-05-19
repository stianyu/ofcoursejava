package my.learning.reflectandannotation;

import java.lang.reflect.Method;

/**
 * 框架类
 */
@Pro(className = "cn.Demo1", methodName = "show")
public class ReflectTest {
    public static void main(String[] args) throws Exception {
        // 1. 解析注解
        //      获取该类的字节码文件对象
        Class<ReflectTest> reflectTestClass = ReflectTest.class;
        //      获取上边的注解对象
        /*
         * 其实就是在内存中生成了一个该注解接口的子类实现对象
                public class ProImpl implements Pro{
                   public String className() {
                       return "cn.Demo1";
                   }
                   public String methodName() {
                       return "show";
                   }
                }
         */
        Pro an = reflectTestClass.getAnnotation(Pro.class);
        String className = an.className();
        String methodName = an.methodName();

        // 2. 加载该类进内存
        Class cls = Class.forName(className);
        // 3. 创建对象
        Object obj = cls.newInstance();
        // 5. 获取方法对象
        Method method = cls.getMethod(methodName);
        // 6. 执行方法
        method.invoke(obj);

    }
}
