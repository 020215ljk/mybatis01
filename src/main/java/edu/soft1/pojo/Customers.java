package edu.soft1.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customers {
    private int CustID;
    private String CustName;
    private String LoginPwd;
    private String phone;
    private String Email;
    private String address;
    private String Account;
}
