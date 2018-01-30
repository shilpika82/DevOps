package com.capgemini.service;

import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;

public interface AccountService {

	Account createAccount(int accountNumber, int amount) throws InsufficientInitialAmountException;
	
	int depositAmt(int accountNumber,int amount) throws InvalidAccountNumberException;
	int depositException(int accountNumber,int amount) throws InvalidAccountNumberException;
	int WithdrawSuccess(int accountNumber,int amount);
	int WithdrawInsufficientBalanceException(int accountNumber,int amount);
	int WithdrawInvalidAccountException (int accountNumber,int amount) throws InvalidAccountNumberException;

}