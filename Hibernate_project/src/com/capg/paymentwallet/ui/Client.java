package com.capg.paymentwallet.ui;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.bean.WalletTransaction;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.service.AccountServiceImpl;
import com.capg.paymentwallet.service.IAccountService;

public class Client {
	   
	IAccountService service=new AccountServiceImpl();
	CustomerBean customer=new CustomerBean();
	Scanner scanner=new Scanner(System.in);
	
	
	public static void main(String[] args) throws Exception {
		
		Client client=new  Client();
		while(true)
		{
		System.out.println("Bank Wallet application");
		System.out.println("\t1. Create Account\t");
		System.out.println("\t2. Show Balance\t");
		System.out.println("\t3. Deposit\t");
		System.out.println("\t4. Withdraw\t");
		System.out.println("\t5. Fund Transfer\t");
		System.out.println("\t6. Print Transactions\t");
		System.out.println("\t7. Exit\t");
		System.out.println("\tChoose an option\t");
		int option =client. scanner.nextInt();
		
		switch (option) {
		case 1:client.create();
               break;
		case 2:client.showbalance();

			break;

		case 3:client.deposit();

			break;
			
			
		case 4:client.withdraw();

			break;
			
	
		case 5:client.fundtransfer();

			break;
			
		
		case 6:client.printTransaction();

			break;
		case 7:System.exit(0);

			break;
			
			
		default:System.out.println("Invalid option");
			break;
		}
		
		}

		
	}
	
	
	void create() throws Exception
	{
		
		System.out.print("\tEnter Customer firstname\t:\t");
		String fname=scanner.next();
		
		System.out.print("\tEnter Customer lastname\t\t:\t");
		String lname=scanner.next();
		
		System.out.print("\tEnter  Customer  email id\t:\t");
		String email=scanner.next();
		
		System.out.print("\tEnter  Customer  phone number\t:\t");
		String phone=scanner.next();
		
		System.out.print("\tEnter  Customer PAN number\t:\t");
		String pan=scanner.next();
		
		System.out.print("\tEnter  Customer  address\t:\t");
		String address=scanner.next();
		
		
		CustomerBean customerBean=new CustomerBean();
		customerBean.setAddress(address);
		customerBean.setEmailId(email);
		customerBean.setPanNum(pan);
		customerBean.setPhoneNo(phone);
		customerBean.setFirstName(fname);
		customerBean.setLastName(lname);
		
		
		
		
		System.out.print("\tEnter Date of Opening (DD/MM/YYYY)\t:\t");
		String accDateInput=scanner.next();
		
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		Date dateOfOpening=sdf.parse(accDateInput);
		
		
		System.out.print("\tEnter balance to create account\t:\t");
		double balance=scanner.nextDouble();
		
		AccountBean accountBean=new AccountBean();
		accountBean.setBalance(balance);
		accountBean.setInitialDeposit(balance);
		accountBean.setCustomerBean(customerBean);;
		
		

		try
		{
		boolean result=service.createAccount(accountBean);
		if(result)
		{
			System.out.println("\n\n\t\tCongratulations Customer account has been created successfully...\n\n\t");
		}
		else
		{
			System.out.println("\n\nEnter valid details..\n\n");
		}
		}
		catch(CustomerException ce)
		{
			System.out.println(ce.getMessage());
		}
		
		
	}
	
	
	void showbalance() throws  Exception 
	{
		System.out.print("\tEnter Account ID\t:\t");
		int accId=scanner.nextInt();
		
		AccountBean accountBean=service.findAccount(accId);
		
		if(accountBean==null){
			System.out.println("\n\nAccount Does not exist\n\n");
			return ;
		}
		System.out.println("\tName: "+accountBean.getCustomerBean().getFirstName());
		double balance=accountBean.getBalance();
				
		
		System.out.println("Your balance is: " +balance);
		
			
		
	}
	
	void deposit() throws Exception
	{
		System.out.print("\tEnter Account ID\t:\t");
		int accId=scanner.nextInt();
		
		AccountBean accountBean=service.findAccount(accId);
		System.out.print("\tFirstName\t:\t"+accountBean.getCustomerBean().getFirstName()+"\n");
		System.out.print("\tLastName\t:\t"+accountBean.getCustomerBean().getLastName()+"\n");
		System.out.print("\tFirstName\t:\t"+accountBean.getBalance()+"\n");
		
		
		System.out.print("\tEnter amount that you want to deposit\t:\t");
		double depositAmt=scanner.nextDouble();
		
		WalletTransaction wt=new WalletTransaction();
		wt.setTransactionType(1);
		wt.setTransactionDate(new Date());
		wt.setTransactionAmt(depositAmt);
		wt.setBeneficiaryAccountBean(null);
		
		accountBean.addTransation(wt);
		
		
		
		
		
		if(accountBean==null){
			
			System.out.println("\n\nAccount Does not exist\n\n");
	
		}
		
		
		boolean result=service.deposit(accountBean, depositAmt);
		
		
		if(result){
			System.out.println("\n\n\tDeposited Money into Account\n\n\t");
		}else{
			System.out.println("NOT Deposited Money into Account ");
		}
			
	}
	
	void withdraw() throws Exception
	{
		System.out.println("\tEnter Account ID\t:\t");
		int accId=scanner.nextInt();
		
		AccountBean accountBean=service.findAccount(accId);
		System.out.print("\tFirstName\t:\t"+accountBean.getCustomerBean().getFirstName()+"\n");
		System.out.print("\tLastName\t:\t"+accountBean.getCustomerBean().getLastName()+"\n");
		System.out.print("\tFirstName\t:\t"+accountBean.getBalance()+"\n");
		
		System.out.print("\tEnter amount that you want to withdraw\t:\t");
		double withdrawAmt=scanner.nextDouble();
		
		
		
		WalletTransaction wt=new WalletTransaction();
		wt.setTransactionType(2);
		wt.setTransactionDate(new Date());
		wt.setTransactionAmt(withdrawAmt);
		wt.setBeneficiaryAccountBean(null);
		
		accountBean.addTransation(wt);
		
		
		if(accountBean==null){
			System.out.println("\n\nAccount Does not exist\n\n");
			
		
		}
		
		
		boolean result=service.withdraw(accountBean, withdrawAmt);
		if(result){
			System.out.println("\n\n\tWithdraw Money from Account done\n\n\t");
		}else{
			System.out.println("\n\n\tWithdraw Money from Account Failed\n\n\t");
		}
		
	}
	
	void fundtransfer() throws Exception
	{
		System.out.print("\tEnter Account ID to Transfer Money From\t:\t");
		int srcAccId=scanner.nextInt();
		
		AccountBean accountBean1=service.findAccount(srcAccId);
		System.out.print("\tFirstName\t:\t"+accountBean1.getCustomerBean().getFirstName()+"\n");
		System.out.print("\tLastName\t:\t"+accountBean1.getCustomerBean().getLastName()+"\n");
		System.out.print("\tFirstName\t:\t"+accountBean1.getBalance()+"\n");
		
		
		System.out.print("\tEnter Account ID to Transfer Money to\t:\t");
		int targetAccId=scanner.nextInt();
		
		AccountBean accountBean2=service.findAccount(targetAccId);
		
		System.out.println("\tEnter amount that you want to transfer\t:\t");
		double transferAmt=scanner.nextDouble();
		
		WalletTransaction wt=new WalletTransaction();
		wt.setTransactionType(3);
		wt.setTransactionDate(new Date());
		wt.setTransactionAmt(transferAmt);
		wt.setBeneficiaryAccountBean(accountBean2);
		
		accountBean1.addTransation(wt);
		
		
		
		boolean result=service.fundTransfer(accountBean1, accountBean2, transferAmt);
		
		if(result){
			System.out.println("\n\nTransfering Money from Account done\n\n");
		}else{
			System.out.println("\n\nTransfering Money from Account Failed\n\n");
		}
		
	}
	
	
	void printTransaction() throws Exception
	{
		System.out.print("\tEnter Account ID for printing Transaction Details\t:\t");
		int accId=scanner.nextInt();
		
		AccountBean accountBean=service.findAccount(accId);
		
		List<WalletTransaction>  transactions=accountBean.getAllTransactions();
		
		System.out.println(accountBean);
		System.out.println("\n\n\tTransaction Details...\n\n\t");
		System.out.println("\tFirstName\t:\t"+accountBean.getCustomerBean().getFirstName());
		System.out.println("\tLastName\t:\t"+accountBean.getCustomerBean().getLastName());
		System.out.println("\tPhone num\t:\t"+accountBean.getCustomerBean().getPhoneNo());
		System.out.println("\tEmailId\t\t:\t"+accountBean.getCustomerBean().getEmailId());
		System.out.println("\tAddress\t\t:\t"+accountBean.getCustomerBean().getAddress());
		System.out.println("\tPAN number\t:\t"+accountBean.getCustomerBean().getPanNum());
		
		
		
		System.out.println("------------------------------------------------------------------");
		System.out.println("\tType\t\tDate\t\tAmount");
		System.out.println("------------------------------------------------------------------");
		for(WalletTransaction wt:transactions){
			
			String str="";
			if(wt.getTransactionType()==1){
				str=str+"\tDEPOSIT";
			}
			if(wt.getTransactionType()==2){
				str=str+"\tWITHDRAW";
			}
			if(wt.getTransactionType()==3){
				str=str+"\tFUND TRANSFER";
			}
			
			str=str+"\t\t"+wt.getTransactionDate();
			
			str=str+"\t\t"+wt.getTransactionAmt();
			System.out.println(str);
		}
		
		System.out.println("------------------------------------------------------------------");
	
	}
	
	    
	
}
