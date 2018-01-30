package com.capgemini.service;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.MockitoAnnotations.Mock;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {
	
	/* (non-Javadoc)
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */
	
	@Mock
	AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	public Account createAccount(int accountNumber,int amount) throws InsufficientInitialAmountException
	{
		int a,b,c;
		if(amount<500)
		{
			throw new InsufficientInitialAmountException();
		}
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		
		account.setAmount(amount);
		 
		if(accountRepository.save(account))
		{
			return account;
		}
	     
		return null;
		
	}
	
	public int depositAmt(int accountNumber,int amount) throws InvalidAccountNumberException
	{
		Account account1= new Account();
		account1.setAccountNumber(101);
		account1.setAmount(200);
		when(accountRepository.searchAccount(accountNumber)).thenReturn(account1);
		if(account1.getAccountNumber()!=0){
			amount=account1.getAmount()+amount;
		}
		System.out.println("new amount is"+amount);
		return amount;
		
	}
	
	@SuppressWarnings("unchecked")
	public int depositException(int accountNumber,int amount) throws InvalidAccountNumberException
	{
		when(accountRepository.searchAccount(accountNumber)).thenThrow(InvalidAccountNumberException.class);
		
		//throw new InvalidAccountNumberException() ;
		return 0;
		
	}
	
	public int WithdrawSuccess(int accountNumber,int amount){
		Account account = new Account();
		when(accountRepository.searchAccount(accountNumber)).thenReturn(account);
		if(account.getAccountNumber()!=0){
			if(account.getAmount()-amount>500){
			amount=account.getAmount()-amount;
		}
		}
		return amount;
	}

	
	public int WithdrawInsufficientBalanceException(int accountNumber,int amount){
		Account account=new Account();
		if(account.getAmount()-amount<500){
				when(accountRepository.searchAccount(accountNumber)).thenThrow(InsufficientBalanceException.class);
		}
				return 0;
	}
	
	public int WithdrawInvalidAccountException(int accountNumber,int amount) throws InvalidAccountNumberException{
		when(accountRepository.searchAccount(accountNumber)).thenThrow(InvalidAccountNumberException.class);
		throw new InvalidAccountNumberException();
	}
}
