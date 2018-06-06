package com.example.demo2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import domain.Address;

@Entity
public class Customer
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String firstname;
	private String lastname;
	@OneToOne
	@JoinColumn
	private Address address;
	
	private String  emailAddress;
	
	protected Customer() {};
	public Customer(String firstname, String lastname, String emailAddress)
	{
	   this.firstname = firstname;
	   this.lastname = lastname;
	   this.emailAddress = emailAddress;
	   
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	
	

	

	
	
	
}
