package com.capg.paymentwallet.exception;

public class CustomerExceptionMessage extends CustomerException {

	public static final String fNameERROR ="First Name should be more than 4 characters";
	public static final String lNameERROR ="Last Name should be more than 4 characters";
	public static final String phoneNumERROR = "phone number should be 10 digits only";
	public static final String mailIdERROR = "Mail id should be valid one";
	public static final String panERROR = "pan number should be in Upper case and in digits(length should be 10)";
	public static final String addressERROR = "Address should not be null";
	public static final String balanceERROR = "Balance should be greater than 500";

}
