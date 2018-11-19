package com.capg.paymentwallet.dao;

import com.capg.paymentwallet.bean.AccountBean;



public interface IAccountDao {


    public boolean createAccount(AccountBean accountBean) throws Exception ;
    public boolean updateAccount(AccountBean accountBean) throws Exception;
    public AccountBean findAccount(int accountId) throws Exception;
  
  
	
	 
    
}
