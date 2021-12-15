package edu.soft1.dao;

import edu.soft1.pojo.Customers;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CustomersTest {

    //查ID
    @Test
    public void testQuery() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        CustomersDao customersDao = sqlSession.getMapper(CustomersDao.class);

        Customers customers = customersDao.findCustById(1);

        System.out.println("查询结果="+customers);

    }

    //查用户名和密码
    @Test
    public void testQueryNP() throws IOException{
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        CustomersDao customersDao = sqlSession.getMapper(CustomersDao.class);

        Customers customers = customersDao.findCust("peter","peter_love$book");

        System.out.println("查询结果="+customers);
    }


    //查所有
    @Test
    public void testAll() throws IOException{
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        CustomersDao customersDao = sqlSession.getMapper(CustomersDao.class);

        List<Customers> customers = customersDao.findAllCusts();

        for (Customers cust : customers) {
            System.out.println("查询结果="+cust);
        }



    }

    //添加
    @Test
    public void testAdd() throws IOException{
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        CustomersDao customersDao = sqlSession.getMapper(CustomersDao.class);

        customersDao.addCust("asdf","1234");

        sqlSession.commit();//提交事物（增删改事务需要）
    }


    @Test
    public void testDelete() throws IOException{
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        CustomersDao customersDao = sqlSession.getMapper(CustomersDao.class);

        customersDao.delCust(13);

        sqlSession.commit();//提交事物（增删改事务需要）
    }


    @Test
    public void testUpdate() throws IOException{
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        CustomersDao customersDao = sqlSession.getMapper(CustomersDao.class);

        customersDao.updateCust(1,"123456789991");
        sqlSession.commit();//提交事物（增删改事务需要）

    }

}
