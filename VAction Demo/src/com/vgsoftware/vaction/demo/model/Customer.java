package com.vgsoftware.vaction.demo.model;

import java.io.Serializable;

public class Customer implements Serializable
{
	private String _firstName = null;
	private String _lastName = null;
	private int _age = 0;
	private int _id = 0;

	public int getId()
	{
		return _id;
	}

	public void setId(int id)
	{
		_id = id;
	}

	public String getFirstName()
	{
		return _firstName;
	}

	public void setFirstName(String firstName)
	{
		_firstName = firstName;
	}

	public String getLastName()
	{
		return _lastName;
	}

	public void setLastName(String lastName)
	{
		_lastName = lastName;
	}

	public int getAge()
	{
		return _age;
	}

	public void setAge(int age)
	{
		_age = age;
	}
}
