package com.sdzee.beans;

public class Coyote {

	private String firstName;
	private String lastName;
	private boolean genius;
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public boolean isGenius() {
		return genius;
	}
	
	public void setGenius(boolean genius) {
		/* No one can argue the fact that Wile E. Coyote is a real genius! */
		this.genius = true;
	}
}
