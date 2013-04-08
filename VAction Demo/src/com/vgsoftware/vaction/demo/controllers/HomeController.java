package com.vgsoftware.vaction.demo.controllers;

import com.vgsoftware.vaction.controller.Controller;
import com.vgsoftware.vaction.result.ActionResult;
import com.vgsoftware.vaction.result.ViewResult;

public class HomeController extends Controller
{
	public ActionResult index()
	{
		getViewData().put("servername", getRequest().getServerName());
		return new ViewResult();
	}
}
