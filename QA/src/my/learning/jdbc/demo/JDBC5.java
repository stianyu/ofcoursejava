package my.learning.jdbc.demo;

import java.util.List;

/**
 * 使用JDBCTemplate把数据库连接池
 * （实际开发用DAO（DateBase Access Object)来写： 数据访问对象，JavaEE设计模式）
 * 创建一个包叫DAO，里面创建一个接口，操作什么表把首字母大写，
 */
public class JDBC5 {
    public static void main(String[] args) {
        MyJDBCTemplate template = new MyJDBCTemplate(JDBCUtils2.getDs());
        String sql = "select * from account where id = ?";
        List<Account> accountList = template.queryForList(sql, Account.class, 1);
        System.out.println(accountList);
    }
}
