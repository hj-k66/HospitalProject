package com.dbexercise;

import com.dbexercise.dao.UserDao;
import com.dbexercise.dao.UserDaoFactory;
import com.dbexercise.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {
    @Autowired
    ApplicationContext context;

    UserDao userDao;
    User user1;
    User user2;
    User user3;
    @BeforeEach
    void setUp(){
        this.userDao = context.getBean("awsUserDao", UserDao.class);
        this.user1 = new User("1","김희정","123456");
        this.user2 = new User("2","라이언","qwer");
        this.user3 = new User("3","멋쟁이","asdf");
    }
    @Test
    void findAll() {
        userDao.deleteAll();
        List<User> userList = userDao.findAll();
        Assertions.assertEquals(0,userList.size());
        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);
        userList = userDao.findAll();
        Assertions.assertEquals(3, userList.size());

    }
    @Test
    void addAndSelect() {
        User user = new User("1","김희정","123456789");

        userDao.deleteAll();
        Assertions.assertEquals(0, userDao.getCount());

        userDao.add(user);
        Assertions.assertEquals(1, userDao.getCount());

        User selectedUser = userDao.getById(user.getId());
        Assertions.assertEquals(user.getName(), selectedUser.getName());
        Assertions.assertEquals(user.getId(), selectedUser.getId());
        Assertions.assertEquals(user.getPassword(), selectedUser.getPassword());
    }

    @Test
    void count(){

        userDao.deleteAll();
        Assertions.assertEquals(0, userDao.getCount());

        userDao.add(user1);
        Assertions.assertEquals(1, userDao.getCount());
        userDao.add(user2);
        Assertions.assertEquals(2, userDao.getCount());
        userDao.add(user3);
        Assertions.assertEquals(3, userDao.getCount());

    }

    @Test
    void getById(){
        Assertions.assertThrows(EmptyResultDataAccessException.class,()->{
            userDao.getById("100");
        });
    }


}