package com.dbexercise;

import com.dbexercise.dao.UserDao2;
import com.dbexercise.dao.UserDaoFactory;
import com.dbexercise.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

class UserDao2Test {
    @Test
    void addAndSelect() throws SQLException, IOException, ClassNotFoundException {
//        UserDao2 userDao2 = new UserDao2(new AwsConnectionMaker());
        UserDao2 userDao2 = new UserDaoFactory().awsUserDao();
        String id = "14";
        User user = new User(id,"do","okokok");
        userDao2.add(user);

        User selectedUser = userDao2.getById(id);
        Assertions.assertEquals("do", selectedUser.getName());
    }
}