package com.capgemini.paytm.junittest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.paytm.beans.Customer;
import com.capgemini.paytm.beans.Wallet;
import com.capgemini.paytm.exception.InvalidInputException;
import com.capgemini.paytm.service.WalletService;
import com.capgemini.paytm.service.WalletServiceImpl;

import static org.junit.Assert.*;
public class WalletTest {
	WalletService service;
	Customer customer1,customer2,customer3, customer4;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Before
	public void setUp(){
		
		Map<String,Customer> data= new HashMap<String, Customer>();
		
		customer1=new Customer("abc", "9866996056",new Wallet(new BigDecimal(19000)));
		customer2=new Customer("someone", "9999999999",new Wallet(new BigDecimal(26000)));
		customer3=new Customer("xyz", "7568432238",new Wallet(new BigDecimal(5000)));
		customer4 = new Customer("Qwerty", "8519989567",new Wallet(new BigDecimal(7000)));
						
					
		data.put("9866996056", customer1);
		data.put("9999999999", customer2);	
		data.put("7568432238", customer3);
		data.put("8519989567", customer4);
			 
		service= new WalletServiceImpl(data);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testCreateAccount() {
		service.createAccount(null,null,null);
	}
	
	@Test
	public void testCreateAccount1() {
		Customer c=new Customer();
		Customer cust=new Customer();
		cust=service.createAccount("fogg","7654323456",new BigDecimal(9000));
		c.setMobileNo("7654323456");
		c.setName("fogg");
		c.setWallet(new Wallet(new BigDecimal(9000)));
		Customer actual =c;
		Customer expected=cust;
		
		assertEquals(expected, actual);
		
	}
	
	@Test	
	public void testCreateAccount2() {
		
		Customer cust=new Customer();
		cust=service.createAccount("abc","98669996056",new BigDecimal(19000));
		
		assertEquals("abc", cust.getName());
		
	}
	@Test
	public void testCreateAccount3() {
		
		Customer cust=new Customer();
		cust=service.createAccount("asdf","9812346578",new BigDecimal(19000));
		assertEquals("9812346578", cust.getMobileNo());
		
	}
	
	@Test(expected=NullPointerException.class)
	public void testCreateAccount4() 
	{
		service.createAccount(null, null, new BigDecimal(1500));
	}
	
	
	@Test
	public void testShowBalance1() {
		
		Customer cust=new Customer();
		cust=service.showBalance("9866996056");
		BigDecimal actual=cust.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(19000);
		
		assertEquals(expected, actual);

	}
	
	@Test
	public void testShowBalance2() {
		
		Customer cust=new Customer();
		
		cust=service.showBalance("9999999999");
		assertNotEquals(cust, customer3);

	}
	@Test
	public void testShowBalance3() {
		
		Customer cust=new Customer();
		cust=service.showBalance("9999999999");
		BigDecimal actual=cust.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(26000);
		assertEquals(expected, actual);

	}

	@Test(expected=InvalidInputException.class)
	public void testFundTransfer() {
		service.fundTransfer(null, null,new BigDecimal(7000));
	}
	

	@Test
	public void testFundTransfer2() {
		customer1=service.fundTransfer("9866996056","9999999999",new BigDecimal(2000));
		BigDecimal actual=customer1.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(28000);
		assertEquals(expected, actual);
	}
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer3() 
	{
		service.fundTransfer("9922950519", null, new BigDecimal(50));		
	}
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer4() 
	{
		service.fundTransfer("", "", new BigDecimal(9000));		
	}
	
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer5() 
	{
		service.fundTransfer("", null, new BigDecimal(800));		
	}
	
	@Test
	public void testDeposit()
	{
		Customer cust = new Customer();
		cust=service.depositAmount("9999999999", new BigDecimal(400));
		BigDecimal actual=cust.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(26400);
		assertEquals(expected, actual);
		
	}
		
	@Test
	public void testDeposit2()
	{
		customer1=service.depositAmount("7568432238", new BigDecimal(2000));
		BigDecimal actual=customer1.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(7000);
		assertEquals(expected, actual);
	}
	
	

	@Test(expected=InvalidInputException.class)
	public void testDepositAmount3() 
	{
		service.depositAmount(null, new BigDecimal(500));
	}
	
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount4() 
	{
		service.depositAmount( " ", new BigDecimal(500));
	}
	
	
	@Test
	public void testWithdraw()
	{
		Customer cust=new Customer();
		cust = service.withdrawAmount("7568432238", new BigDecimal(2000));
		BigDecimal actual=cust.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(3000);
		
		assertEquals(expected, actual);
		
	}
		
	@Test
	public void testWithdraw2()
	{
		customer1=service.withdrawAmount("8519989567", new BigDecimal(2000));
		BigDecimal actual=customer1.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(5000);
		assertEquals(expected, actual);
	}	
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalancenull() 
	{
		service.showBalance(null);		
	}
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalanceSpace() 
	{
		service.showBalance(" ");		
	}
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount() 
	{
		service.depositAmount("9942221102", new BigDecimal(0));
	}
	
	
}
