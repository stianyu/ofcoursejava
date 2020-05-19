package my.learning.jdbc.transaction;


import my.learning.jdbc.demo.JDBCUtils2;

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
}
