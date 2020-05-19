package my.answer.reflectandannotation;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BeanUitls {
    public static void polulate(Map<String, Object> map, Object bean) throws Exception {
        Class beanClass = bean.getClass();
        for(String key : map.keySet()) {
            Object value = map.get(key);
            Method setMethod = beanClass.getMethod("set" + key.substring(0, 1).toUpperCase() + key.substring(1), value.getClass());
            setMethod.invoke(bean, value);
        }
    }

    public static void main(String[] args) throws Exception {
        User user = new User();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1111);
        map.put("name", "张三");
        map.put("createTime", new Date());
        polulate(map, user);
        System.out.println(user);
    }
}

