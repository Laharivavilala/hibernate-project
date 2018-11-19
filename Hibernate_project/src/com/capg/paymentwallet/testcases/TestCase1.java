package com.capg.paymentwallet.testcases;

import static org.junit.Assert.*;

import java.security.Provider.Service;

import org.junit.BeforeClass;
import org.junit.Test;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.service.AccountServiceImpl;
import com.capg.paymentwallet.service.IAccountService;

public class TestCase1 {

	
	static IAccountService service = null;
	@BeforeClass
	public static void createInstance() 
	{
		service = new AccountServiceImpl();
	}

	@Test
	public  void test() throws Exception
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("lahari");
		customer.setLastName("vavilala");
	    customer.setAddress("chennai");
	    customer.setPhoneNo("9441929956");
		customer.setEmailId("lahari@capgemini.com");
		customer.setPanNum("REJDG12345");
		AccountBean bean = new AccountBean();
		bean.setAccountId(101);
		bean.setBalance(500.00);
		bean.setCustomerBean(customer);
		boolean result = service.createAccount(bean);
		
		assertTrue(result);
	}
	
	@Test (expected = CustomerException.class)
	public  void testFirstNameNull() throws Exception
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("");
		customer.setLastName("vavilala");
		 customer.setPhoneNo("9441929956");
		customer.setEmailId("lahari@capgemini.com");
		customer.setPanNum("BVBPK9214B");
		AccountBean bean = new AccountBean();
		bean.setAccountId(101);
		bean.setBalance(500.00);
		bean.setCustomerBean(customer);
		boolean result =service.createAccount(bean);
		assertFalse(result);
	}
	
	@Test (expected = CustomerException.class)
	public void testFirstNameForOnlyChar() throws Exception //First name only characters testcase
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("lah@#");
		customer.setLastName("vavilala");
		customer.setPhoneNo("9441929956");
		customer.setEmailId("lahari@capgemini.com");
		customer.setPanNum("BVBPK9214B");
		
		AccountBean bean = new AccountBean();
		bean.setAccountId(101);
		bean.setBalance(500.00);
		bean.setCustomerBean(customer);
		boolean result = service.createAccount(bean);
		assertFalse(result);
	}
	
	@Test (expected = CustomerException.class)
	public void testFirstNameForlength() throws Exception //First name only characters testcase
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("la");
		customer.setLastName("vavilala");
		customer.setPhoneNo("9441929956");
		customer.setEmailId("lahari@capgemini.com");
		customer.setPanNum("BVBPK9214B");
		AccountBean bean = new AccountBean();
		bean.setAccountId(101);
		bean.setBalance(500.00);
		bean.setCustomerBean(customer);
		boolean result = service.createAccount(bean);
		assertFalse(result);
	}
	
	// Testcases for lastname

	@Test (expected =  CustomerException.class)
	public void testLastNameNull() throws Exception //Last name not null testcase
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("lahari");
		customer.setLastName("");
		customer.setPhoneNo("9441929956");
		customer.setEmailId("lahari@capgemini.com");
		customer.setPanNum("BVBPK9214B");
		AccountBean bean = new AccountBean();
		bean.setAccountId(101);
		bean.setBalance(500.00);
		bean.setCustomerBean(customer);
		boolean result = service.createAccount(bean);
		assertFalse(result);
	}
	
	@Test (expected = CustomerException.class)
	public void testLastNameForOnlyChar() throws Exception //Last name only characters testcase
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("lahari");
		customer.setLastName("vavi#$la");
		customer.setPhoneNo("9441929956");
		customer.setEmailId("lahari@capgemini.com");
		customer.setPanNum("BVBPK9214B");
		AccountBean bean = new AccountBean();
		bean.setAccountId(101);
		bean.setBalance(500.00);
		bean.setCustomerBean(customer);
		boolean result =service.createAccount(bean);
		assertFalse(result);
	}
	
	@Test (expected = CustomerException.class)
	public void testLastNameForlength() throws Exception //Last name only characters (>3) testcase
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("lahari");
		customer.setLastName("Na");
		customer.setPhoneNo("9441929956");
		customer.setEmailId("lahari@capgemini.com");
		customer.setPanNum("BVBPK9214B");
		AccountBean bean = new AccountBean();
		bean.setAccountId(101);
		bean.setBalance(500.00);
		bean.setCustomerBean(customer);
		boolean result = service.createAccount(bean);
		assertFalse(result);
	}
	
	//Test cases for phone number
	
	@Test (expected = CustomerException.class)
	public void testPhoneNumberForlength() throws Exception //phone number should be 10 digits testcase
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("lahari");
		customer.setLastName("vavilala");
		customer.setPhoneNo("9441929956");
		customer.setEmailId("lahari@capgemini.com");
		customer.setPanNum("BVBPK9214B");
		
		AccountBean bean = new AccountBean();
		bean.setAccountId(101);
		bean.setBalance(500.00);
		bean.setCustomerBean(customer);
		boolean result = service.createAccount(bean);
		assertFalse(result);
	}
	
	
	
	/*@Test (expected = WalletException.class)
	public void testPhoneNumberForChar() throws WalletException //Phone Number only characters testcase
	{	
		Customer customer = new Customer();
		customer.setFirstName("vinitha");
		customer.setLastName("Narvaneni");
		customer.setPhoneNum(new BigInteger("97036ab123"));
		customer.setEmailID("vinitha.narvaneni@capgemini.com");
		customer.setPermanantAccNum("BVBPK9214B");
		boolean result =service.createAccount(customer);
		assertFalse(result);
	}*/
	
	
	@Test (expected = CustomerException.class)
	public void testEmailIDForNull() throws Exception //EmailID only null
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("vinitha");
		customer.setLastName("Narvaneni");
		customer.setPhoneNo("9441929956");
		customer.setEmailId("");
		customer.setPanNum("BVBPK9214B");
		AccountBean bean = new AccountBean();
		bean.setAccountId(101);
		bean.setBalance(500.00);
		bean.setCustomerBean(customer);
		boolean result =service.createAccount(bean);
		assertFalse(result);
	}

	@Test (expected = CustomerException.class)
	public void testPanForSpecialCharacters() throws Exception //Pan for special characters
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("vinitha");
		customer.setLastName("Narvaneni");
		customer.setPhoneNo("9441929956");
		customer.setEmailId("lahari@capgemini.com");
		customer.setPanNum("BVDA%&1ERF");
		AccountBean bean = new AccountBean();
		bean.setAccountId(101);
		bean.setBalance(500.00);
		bean.setCustomerBean(customer);
		boolean result = service.createAccount(bean);
		assertFalse(result);
	}
	
	@Test(expected =CustomerException.class)
	public void testPanForNull() throws Exception //Pan for null
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("lahari");
		customer.setLastName("vavilala");
		customer.setPhoneNo("9441929956");
		customer.setEmailId("lahari@capgemini.com");
		customer.setPanNum("");
		AccountBean bean = new AccountBean();
		bean.setAccountId(101);
		bean.setBalance(500.00);
		bean.setCustomerBean(customer);
		boolean result = service.createAccount(bean);
		assertFalse(result);
	}
	
	
	
	@Test (expected = CustomerException.class)
	public void testPanForLength() throws Exception
	{	
		CustomerBean customer = new CustomerBean();
		customer.setFirstName("lahari");
		customer.setLastName("vavilala");
		customer.setPhoneNo("9441929956");
		customer.setEmailId("lahari@capgemini.com");
		customer.setPanNum("BVGH567");
		AccountBean bean = new AccountBean();
		bean.setAccountId(101);
		bean.setBalance(500.00);
		bean.setCustomerBean(customer);
		boolean result =service.createAccount(bean);
		assertFalse(result);
	}
	
	
	
}



