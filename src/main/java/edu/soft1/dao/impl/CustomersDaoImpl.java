package edu.soft1.dao.impl;

import edu.soft1.dao.CustomersDao;
import edu.soft1.pojo.Customers;

import java.util.List;

public class CustomersDaoImpl implements CustomersDao {
    @Override
    public Customers findCustById(int custid) {
        return null;
    }

    @Override
    public Customers findCust(String CustName, String LoginPwd) {
        return null;
    }

    @Override
    public List<Customers> findAllCusts() {
        return null;
    }

    @Override
    public void addCust(String CustName, String LoginPwd) {
    }

    @Override
    public void delCust(int CustID) {

    }

    @Override
    public void updateCust(int custID, String phone) {

    }

}
