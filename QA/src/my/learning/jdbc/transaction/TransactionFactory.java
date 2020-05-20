package my.learning.jdbc.transaction;


import my.learning.jdbc.demo.JDBCUtils2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TransactionFactory {

    private static TransactionManager transactionManager;
    private static ConnectionUtils connectionUtils;

    static {
        connectionUtils = new ConnectionUtils();
        connectionUtils.setDataSource(JDBCUtils2.getDs());

        transactionManager = new TransactionManager();
        transactionManager.setConnectionUtils(connectionUtils);
    }

    public static TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public static ConnectionUtils getConnectionUtils(){
        return connectionUtils;
    }

    // 返回一个代理对象，对一个service对象提供事务增强，生成一个service代理对象（动态代理）。代理对象实现了事务控制，和真实对象实现转账功能，一起实现了事务控制的转账
    public static Object enableTransaction(Object obj) {
        Object object = Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            // 代理逻辑
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                try {
                    transactionManager.beginTransaction();
                    Object ret = method.invoke(obj, args);  // 把这里整个替换成transfer方法
                    transactionManager.commit();
                    return ret;
                } catch (Exception e) {
                    e.getCause().printStackTrace();
                    transactionManager.rollback();
                    return null;
                } finally {
                    transactionManager.release();
                }
            }
        });
        return object;
    }
}
