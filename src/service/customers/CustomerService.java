package service.customers;

import model.customer.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
	private static final CustomerService CUSTOMER_SERVICE = new CustomerService();
	private final Map<String, Customer> customerList = new HashMap<String, Customer>();

	private CustomerService() {
	}

	public static CustomerService getCustomerServiceInstance() {
		return CUSTOMER_SERVICE;
	}

	public void addCustomer(String email, String firstName, String lastName) {
		Customer newCustomer = new Customer(firstName, lastName, email);
		customerList.put(email, newCustomer);
	}

	public Customer getCustomer(String customerEmail) {
		return customerList.get(customerEmail);
	}

	public Collection<Customer> getAllCustomer() {
		return customerList.values();
	}

}
