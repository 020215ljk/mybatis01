package edu.soft1.util;

import edu.soft1.dao.CustomersDao;
import edu.soft1.pojo.Customers;
import org.junit.Test;

import java.io.IOException;

public class CustomersTestWithUtil {

    @Test
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
    }

}

