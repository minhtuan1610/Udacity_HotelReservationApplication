package service;

import model.customer.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
	private Map<String, Customer> customerList = new HashMap<String, Customer>();

	public CustomerService() {
	}

	public void addCustomer(String email, String firstName, String lastName) {

	}

	public Customer getCustomer(String customerEmail) {
		return null;
	}

	public Collection<Customer> getAllCustomer() {
		return null;
	}

}
