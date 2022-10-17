package com.dbexercise;

import java.sql.*;
import java.util.Map;

public class UserDao2 {
    public void add() throws SQLException, ClassNotFoundException {
        //환경변수로 DB 설정(보안위해)
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);  //db연동
        PreparedStatement ps = conn.prepareStatement("SELECT * from users where id = ?"); //sql문 템플릿
        ps.setString(1,"0");

        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            System.out.printf("id : %s, name : %s, password : %s", id,name,password);
        }
        //connection끊기
        ps.close();
        conn.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao2 userDao2 = new UserDao2();
        userDao2.add();
    }
}
