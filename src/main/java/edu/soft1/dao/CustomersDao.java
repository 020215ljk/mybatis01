package edu.soft1.dao;

import edu.soft1.pojo.Customers;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomersDao {
    //查
    public Customers findCustById(int custID);
    public Customers findCust(@Param(value = "CustName") String a, @Param(value = "LoginPwd") String b);
    public List<Customers> findAllCusts();

    //增
    public void addCust(@Param(value = "name") String CustName, @Param(value = "pwd") String LoginPwd);

    //删
    public void delCust(@Param(value = "id") int custID);

    //改
    public void updateCust(@Param(value = "id") int custID, @Param(value = "name") String CustName,
                           @Param(value = "phone") String phone, @Param("add") String address);



}
