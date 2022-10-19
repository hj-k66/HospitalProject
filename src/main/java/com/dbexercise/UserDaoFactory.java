package com.dbexercise;

public class UserDaoFactory {
    //관계 설정 기능
    //조립하기
    public UserDao2 awsUserDao(){
        AwsConnectionMaker awsConnectionMaker = new AwsConnectionMaker();
        UserDao2 userDao2 = new UserDao2(awsConnectionMaker);
        return userDao2;
    }

    public UserDao2 localUserDao(){
        UserDao2 userDao2 = new UserDao2(new LocalConnectionMaker());
        return userDao2;
    }

}
