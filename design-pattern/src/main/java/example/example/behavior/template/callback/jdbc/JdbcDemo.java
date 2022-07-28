package example.example.behavior.template.callback.jdbc;

import java.sql.*;

public class JdbcDemo {
    public void queryUser(Long id) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //1.加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "xzg", "xzg");

            //2.创建statement类对象，用来执行SQL语句
            stmt = conn.createStatement();
            //3.ResultSet类，用来存放获取的结果集
            String sql = "select * from user where id=" + id;
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {

            }
        }catch (Exception e) {

        }finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO: log...
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    // TODO: log...
                }
            }
        }
    }
}
