package my.answer.reflectandannotation;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ApplicationContext {
    private Map<String, Object> map;

    public ApplicationContext() {
    }

    //通过构造方法创建核心容器，其中参数path是配置文件的路径，配置文件默认位于类路径（classpath）下当核心容器创建时，需要将配置文件中的每一行创建成JavaBean实例存入核心容器
    public ApplicationContext(String path) throws Exception {
        map = new HashMap<>();
        Properties pro = new Properties();
        ClassLoader classLoader = ApplicationContext.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream(path);
        pro.load(is);

        for(String key: pro.stringPropertyNames()) {
            Class cls = Class.forName(pro.getProperty(key));
            Object obj = cls.newInstance();
            map.put(key, obj);
        }
    }

    //通过getBean可以获取核心容器中编号为id的JavaBean对象
    public Object getBean(String id) throws Exception {
        return map.get(id);
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ApplicationContext("pro.properties");
        User obj1 = (User)applicationContext.getBean("obj1");
        Shop obj2 = (Shop)applicationContext.getBean("obj2");
        Shop obj3 = (Shop)applicationContext.getBean("obj2");
        System.out.println(obj2 == obj3);
        System.out.println(obj1);
        System.out.println(obj2);
    }
}


