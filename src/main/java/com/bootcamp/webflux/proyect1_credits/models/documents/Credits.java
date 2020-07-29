package com.bootcamp.webflux.proyect1_credits.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "credits")
public class Credits {

	@Id
	private String id;
	
	private String idCustomer;
	
	private String nameCredit;
	
	private Double balance;
	
	private Double amount;
	
	private Double amountLimit;
	
	public Credits() {
	}
	
	public Credits(String idCustomer, String nameCredit, Double balance, Double amount, Double amountLimit) {
		this.idCustomer = idCustomer;
		this.nameCredit = nameCredit;
		this.balance = balance;
		this.amount = amount;
		this.amountLimit = amountLimit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getNameCredit() {
		return nameCredit;
	}

	public void setNameCredit(String nameCredit) {
		this.nameCredit = nameCredit;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmountLimit() {
		return amountLimit;
	}

	public void setAmountLimit(Double amountLimit) {
		this.amountLimit = amountLimit;
	}
	
}
