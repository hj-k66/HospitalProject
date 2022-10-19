package com.dbexercise;

import com.dbexercise.dao.UserDao2;
import com.dbexercise.dao.UserDaoFactory;
import com.dbexercise.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.sql.SQLException;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDao2Test {
    @Autowired
    ApplicationContext context;
    @Test
    void addAndSelect() throws SQLException, IOException, ClassNotFoundException {
//        UserDao2 userDao2 = new UserDao2(new AwsConnectionMaker());
//        UserDao2 userDao2 = new UserDaoFactory().awsUserDao();
        UserDao2 userDao2 = context.getBean("awsUserDao", UserDao2.class);
        String id = "15";
        User user = new User(id,"do","okokok");
        userDao2.add(user);

        User selectedUser = userDao2.getById(id);
        Assertions.assertEquals("do", selectedUser.getName());
    }
}