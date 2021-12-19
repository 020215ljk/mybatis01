package edu.soft1.util;

import edu.soft1.dao.CustomersDao;
import edu.soft1.pojo.Customers;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class CustomersTestWithUtil {
    InputStream is;

    {
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

    SqlSession sqlSession = sqlSessionFactory.openSession();

/*    @Test
    public void testCustomers() throws IOException{
        //        1.将mapper文件转为dao实现类
        CustomersDao customersDao = (CustomersDao) SqlSessionUtil.getMapper(CustomersDao.class);
        //        2.调用接口实现类的方法
        Customers customers = customersDao.findCustById(1);

        //        3.执行事务(根据需要添加此句)
        SqlSessionUtil.commit();//若是增删改，则需要加上
        //        4.关闭SqlSession
        SqlSessionUtil.closeSqlSession();
        //        5.打印查询结果
        System.out.println("查询结果=" + customers);
    }*/


    //增删改操作
    @Test
    public void testCUD(){
        CustomersDao customersDao = (CustomersDao) sqlSession.getMapper(CustomersDao.class);
        customersDao.updateCust(1,"steven","","");
    }

    /**
     * 一级缓存SqlSession
     */
    @Test
    public void testSqlSession(){
//用户A第一次调用findCustById()查询
        System.out.println("==========用户A第一次调用findCustById()查询============");
        CustomersDao customersDao = (CustomersDao) SqlSessionUtil.getMapper(CustomersDao.class);
        Customers customers = customersDao.findCustById(1);
        System.out.println("用户A调用findCustById()查询结果="+customers);
        System.out.println("==========用户A第二次调用findCustById()查询===========");
//用户A第二次调用findCustById()查询
        Customers customers2 = customersDao.findCustById(1);
        System.out.println("用户A调用findCustById()查询结果="+customers2);
    }



    /**
     * 二级缓存
     */
    @Test
    public void testCache(){
//用户A调用findCustById()查询
        System.out.println("==========用户A调用findCustById()查询============");
        SqlSession sq1 = SqlSessionUtil.getSqlSession();
        CustomersDao customersDao = sq1.getMapper(CustomersDao.class);
        Customers customers = customersDao.findCustById(1);
        System.out.println("用户A调用findCustById()查询结果="+customers);
//        sq1.close();//需要把SqlSession关闭之后，查询结果才会放入二级缓存。
        System.out.println("=================================");
/*
在用户A和用户B之间的CUD(增删改)操作，
需要执行事务提交commit()，
才会清空二级缓存cache。
避免用户B查询结果出现"脏数据"
*/
//用户B调用findCustById()查询
        System.out.println("==========用户B调用findCustById()查询============");
        SqlSession sq2 = SqlSessionUtil.getSqlSession();
        CustomersDao customersDao2 = sq2.getMapper(CustomersDao.class);
        Customers customers2 = customersDao2.findCustById(1);
        System.out.println("用户B调用findCustById()查询结果="+customers2);
    }

}

