package my.learning.jdbc.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BeanUtils {

    /**
     * 将JDBC查询结果集封装成JavaBean对象
     *
     * @param rs  被封装的结果集
     * @param cls 要封装成的Class类
     * @param <T> Class类的泛型 JavaBean类
     * @return 被封装好的JavaBean对象
     * @throws SQLException
     */
    public static <T> T populate(ResultSet rs, Class<T> cls) throws SQLException {
        Constructor<T> constructor = null;
        T obj = null;
        if (!rs.next()) {
            return null;
        }
        try {
            constructor = cls.getConstructor();
            obj = constructor.newInstance();

            Method[] methods = cls.getMethods();
            for (Method method : methods) {
                //方法名
                String mname = method.getName();
                if (mname.startsWith("set")) {
                    char[] chars = mname.toCharArray();
                    // System.out.println("mname: " + mname);
                    chars[3] = Character.toLowerCase(chars[3]);
                    //属性名
                    String pname = new String(chars, 3, chars.length - 3);
                    // System.out.println("pname: " + pname);

                    //set方法参数类型
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length != 1) {//如果不是一个参数的set方法，则不执行
                        continue;
                    }
                    Object pvalue = rs.getObject(pname, parameterTypes[0]);//从结果集中获取与属性名相同的列的值
                    method.invoke(obj, pvalue);
                }
            }
            return obj;

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询多条数据，并将查询数据封装成JavaBean对象，再将beanh装载成List集合
     *
     * @param rs
     * @param cls
     * @param <T>
     * @return
     * @throws SQLException
     */
    public static <T> List<T> populateList(ResultSet rs, Class<T> cls) throws SQLException {
        List<T> list = new ArrayList<>();
        T obj = null;
        while ((obj = populate(rs, cls)) != null) {
            list.add(obj);
        }
        return list;
    }

}
