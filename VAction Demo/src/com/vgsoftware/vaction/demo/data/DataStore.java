package com.vgsoftware.vaction.demo.data;

import java.util.ArrayList;
import java.util.List;

import com.vgsoftware.vaction.demo.model.Customer;

public class DataStore
{
	private static List<Customer> _customerStore = new ArrayList<Customer>();
	private static int _customerId = 0;

	public static List<Customer> listCustomers()
	{
		return _customerStore;
	}

	public static void addCustomer(Customer person)
	{
		if (person.getId() == 0)
		{
			person.setId(++_customerId);
			_customerStore.add(person);
		}
	}

	public static boolean deleteCustomer(int id)
	{
		int pos = -1;
		for (int i = 0; i < _customerStore.size(); i++)
		{
			if (_customerStore.get(i).getId() == id)
			{
				pos = i;
				break;
			}
		}
		if (pos >= 0)
		{
			_customerStore.remove(pos);
			return true;
		}
		return false;
	}
}
