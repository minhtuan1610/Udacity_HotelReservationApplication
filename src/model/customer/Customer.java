package model.customer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
	private static final String REGEX_EMAIL = "^(.+)@(.+).(.+)$";
	private final String firstName;
	private final String lastName;
	private final String email;

	public Customer(String firstName, String lastName, String email) {
		this.isValidEmail(email);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public void isValidEmail(String email) {
		Pattern pattern = Pattern.compile(REGEX_EMAIL);
		Matcher matcher = pattern.matcher(email);
		boolean match = matcher.matches();
		if (!match) {
			throw new IllegalArgumentException("Invalid Email");
		}
	}

	public String getEmail() {
		return email;
	}


	@Override
	public String toString() {
		return "First name: " + firstName + " Last name: " + lastName + " Email: " + getEmail();
	}
}
