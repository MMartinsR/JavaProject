package entities;

import java.text.ParseException;
import util.DateUtils;


public class Client {
	
	
	private static int count = 0;  // counter to increment client id values
	private int id;
	private String name;
	private int nif;
	private String birthDate;
	private String address;
	private double balance;
	
	
	public Client() {
		
	
	}

	public Client (String name, int nif, String birthDate, String address, double inicialDeposit) {
		
		setId(++count);	 // method to create id numbers. This is a private method so, it can only be accessed by this class.	
		this.name = name;
		this.nif = nif;
		this.birthDate = birthDate;
		this.address = address;
		deposit(inicialDeposit);  // method to modify client's account balance.
		
	}
	
	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}
	
	private void setId(int id) {
		
		this.id = id;
	}

	public int getNif() {
		return nif;
	}

	public double getBalance() {
		return balance;
	}
	
	// Deposit method 
	public void deposit(double value) {
		
		this.balance += value;
	}
	
	// Withdraw method
	public void withdraw(double value) {
		
			this.balance -= value;
	
	}
	
	@Override
	public String toString() {  // method to print data on screen.
	
		try {
			return "==========================================="
					+ "\nId Number: " + id +"\n"
					+ "Name: " + name + "\n"
					+ "Age: " + DateUtils.calculateAge(birthDate) + "\n"
					+ "Address: " + address + "\n"
					+ "NIF: " + nif + "\n"
					+ "Balance: " + String.format("€ %.2f", balance) + "\n"
					+ "===========================================";
			
		} catch (ParseException e) {

			e.printStackTrace();
			return "";
		}
	}	

}
