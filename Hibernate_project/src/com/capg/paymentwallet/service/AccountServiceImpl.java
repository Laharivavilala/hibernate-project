package com.capg.paymentwallet.service;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.dao.AccountDAOImpl;
import com.capg.paymentwallet.dao.IAccountDao;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.exception.CustomerExceptionMessage;

public class AccountServiceImpl implements IAccountService{

	@Override
	public boolean createAccount(AccountBean accountBean)
			throws Exception {
		boolean isValid = validate(accountBean);
		boolean result = false;
		if(isValid==true) {
			IAccountDao dao=new AccountDAOImpl();
		 result=dao.createAccount(accountBean);
		 result=true;
		}
		
		return result;
	}

	

	@Override
	public boolean deposit(AccountBean accountBean, double depositAmount)
			throws Exception {
		accountBean.setBalance(accountBean.getBalance()+depositAmount);
		IAccountDao dao=new AccountDAOImpl();
		boolean result=dao.updateAccount(accountBean);
		return result;
	}

	@Override
	public boolean withdraw(AccountBean accountBean, double withdrawAmount)
			throws Exception {
		accountBean.setBalance(accountBean.getBalance()-withdrawAmount);
		IAccountDao dao=new AccountDAOImpl();
		boolean result=dao.updateAccount(accountBean);
		return result;
	}

	@Override
	public boolean fundTransfer(AccountBean transferingAccountBean,
			AccountBean beneficiaryAccountBean, double transferAmount) throws Exception {
		
		transferingAccountBean.setBalance(transferingAccountBean.getBalance()-transferAmount);
		beneficiaryAccountBean.setBalance(beneficiaryAccountBean.getBalance()+transferAmount);
		
		IAccountDao dao=new AccountDAOImpl();
		boolean result1=dao.updateAccount(transferingAccountBean);
		boolean result2=dao.updateAccount(beneficiaryAccountBean);
		return result1 && result2;
	}

	



	@Override
	public AccountBean findAccount(int accountId) throws Exception {
		IAccountDao dao=new AccountDAOImpl();
		AccountBean bean=dao.findAccount(accountId);
		return bean;
	}
	
	
	
	public boolean validate( AccountBean accountBean) throws CustomerException {
	       
	       boolean isValid = true;
	       if(!(accountBean.getCustomerBean().getFirstName().matches("[a-zA-Z]{4,}")))
	       {
	              //isValid=false;
	              throw new CustomerException(CustomerExceptionMessage.fNameERROR);
	       }
	       if(!( accountBean.getCustomerBean().getLastName().matches("[a-zA-Z]{4,}")))
	       {
	              //isValid=false;
	              throw new CustomerException(CustomerExceptionMessage.lNameERROR);
	       }
	       if(!(accountBean.getCustomerBean().getPhoneNo().toString().matches("^[6-9][0-9]{9}"))){
	              
	              //isValid=false;
	              throw new CustomerException(CustomerExceptionMessage.phoneNumERROR);
	       }
	       if((accountBean.getCustomerBean().getEmailId()== null || !(accountBean.getCustomerBean().getEmailId().matches("[a-zA-Z][a-zA-z0-9-.]*@[a-zA-Z0-9]+([.][a-zA-Z)]+)+")))){

	              //isValid=false;
	              throw new CustomerException(CustomerExceptionMessage.mailIdERROR);
	       }
	       if((accountBean.getCustomerBean().getPanNum()==null) || (!(accountBean.getCustomerBean().getPanNum().matches("^[A-Z][A-Z0-9]{9}")))){
	              
	              //isValid=false;
	              throw new CustomerException(CustomerExceptionMessage.panERROR);
	       }
	       if((accountBean.getCustomerBean().getAddress()==null)||(!(accountBean.getCustomerBean().getAddress().matches("[A-Za-z0-9]{5,50}"))))
	       {
	              
	              throw new CustomerException(CustomerExceptionMessage.addressERROR);
	       }
	       if(accountBean.getBalance()==0||!(accountBean.getBalance()>0)){
	              
	              throw new CustomerException(CustomerExceptionMessage.balanceERROR);

	       }
	      
	       
	              return isValid;

	}


}
