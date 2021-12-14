package edu.soft1.dao;

import edu.soft1.pojo.Customers;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class CustomersTest {

    @Test
    public void testQuery() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        CustomersDao customersDao = (CustomersDao) sqlSession.getMapper(CustomersDao.class);

        //查询ID
//        Customers customers = customersDao.findCustById(1);
        //以账号密码查询
//        Customers customers = customersDao.findCust("peter","peter_love$book");
        //插入账号密码
        customersDao.addCust("asdf","1234");
        sqlSession.commit();//提交事物（增删改事务需要）

//        System.out.println("查询结果="+customers);

    }
}
