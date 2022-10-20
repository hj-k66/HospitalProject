package com.dbexercise.dao;

import com.dbexercise.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDao {

    private ConnectionMaker connectionMaker;

    //Constructor에서 초기화
    public UserDao() {
        this.connectionMaker = new AwsConnectionMaker();
    }

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void deleteAll() throws SQLException {
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement("DELETE from users");
        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public int getCount() throws SQLException {
        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement("SELECT count(*) FROM users");

        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        //close
        rs.close();
        ps.close();
        conn.close();

        return count;
    }

    public void add(User user) throws SQLException, ClassNotFoundException, IOException {
        //환경변수로 DB 설정(보안위해)
        Connection conn = connectionMaker.makeConnection();


        Class.forName("com.mysql.cj.jdbc.Driver");
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users(id,name,password) values(?,?,?)"); //sql문 템플릿
        ps.setString(1,user.getId());
        ps.setString(2,user.getName());
        ps.setString(3,user.getPassword());

        ps.executeUpdate(); //Mysql workbench에서 ctrl + enter와 유사
        //connection끊기
        ps.close();
        conn.close();
    }

    public User getById(String id) throws ClassNotFoundException, SQLException {
        //환경변수로 DB 설정(보안위해)
        Connection conn = connectionMaker.makeConnection();


        Class.forName("com.mysql.cj.jdbc.Driver");
        PreparedStatement ps = conn.prepareStatement("SELECT * from users where id = ?"); //sql문 템플릿
        ps.setString(1,id);

        ResultSet resultSet = ps.executeQuery();
        User user = null;
        if(resultSet.next()){
            user = new User(resultSet.getString("id"),resultSet.getString("name"),
                    resultSet.getString("password"));
        }

        //close
        resultSet.close();
        ps.close();
        conn.close();

        if(user == null){
            throw new EmptyResultDataAccessException(1);
        }
        return user;
    }

    public List<User> findAll() throws ClassNotFoundException, SQLException {
        //환경변수로 DB 설정(보안위해)

        Connection conn = connectionMaker.makeConnection();

        PreparedStatement ps = conn.prepareStatement("SELECT * from users"); //sql문 템플릿

        List<User> userList = new ArrayList<>();
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
            User user = new User(resultSet.getString("id"),resultSet.getString("name"),
                    resultSet.getString("password"));
            userList.add(user);
        }

        resultSet.close();
        //connection끊기
        ps.close();
        conn.close();
        return userList;
    }




    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        UserDao userDao = new UserDao();
        userDao.add(new User( "7","park","asdf"));
//        List<User> userList = userDao2.findAll();
//        for (User user: userList) {
//            System.out.printf("id : %s, name : %s, password: %s\n", user.getId(), user.getName(), user.getPassword());
//        }
    }
}
