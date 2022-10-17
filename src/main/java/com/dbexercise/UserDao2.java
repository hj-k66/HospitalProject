package com.dbexercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users(id,name,password) values(?,?,?)"); //sql문 템플릿
        ps.setString(1,"1");
        ps.setString(2,"heejeong");
        ps.setString(3,"11111");

        ps.executeUpdate(); //Mysql workbench에서 ctrl + enter와 유사
        //connection끊기
        ps.close();
        conn.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao2 userDao2 = new UserDao2();
        userDao2.add();
    }
}
