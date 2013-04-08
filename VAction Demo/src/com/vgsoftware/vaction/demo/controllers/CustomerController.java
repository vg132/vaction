package com.vgsoftware.vaction.demo.controllers;

import com.vgsoftware.vaction.annotation.HttpGet;
import com.vgsoftware.vaction.annotation.HttpPost;
import com.vgsoftware.vaction.controller.Controller;
import com.vgsoftware.vaction.demo.data.DataStore;
import com.vgsoftware.vaction.demo.model.Customer;
import com.vgsoftware.vaction.result.ActionResult;
import com.vgsoftware.vaction.result.JsonResult;
import com.vgsoftware.vaction.result.RedirectResult;
import com.vgsoftware.vaction.result.ViewResult;

public class CustomerController extends Controller
{
	@HttpGet
	public ViewResult index()
	{
		ViewResult result = new ViewResult();
		result.setModel(DataStore.listCustomers());
		return result;
	}

	@HttpPost
	public ActionResult addCustomer()
	{
		Customer customer = new Customer();
		customer.setFirstName(getRequest().getParameter("firstName"));
		customer.setLastName(getRequest().getParameter("lastName"));
		customer.setAge(Integer.parseInt(getRequest().getParameter("age")));
		DataStore.addCustomer(customer);
		return view("index", DataStore.listCustomers());
	}

	public RedirectResult deleteCustomer()
	{
		int id = Integer.parseInt(getRequest().getParameter("id"));
		DataStore.deleteCustomer(id);
		RedirectResult result = new RedirectResult();
		result.setController("customer");
		result.setView("index");
		return result;
	}

	public JsonResult dumpCustomers()
	{
		return json(DataStore.listCustomers());
	}
}
