package com.vgsoftware.vaction.result;

import com.google.gson.Gson;
import com.vgsoftware.vaction.ActionContext;
import com.vgsoftware.vaction.view.ViewEngine;

public class JsonResult extends ActionResult
{
	public JsonResult()
	{
		super();
	}

	public JsonResult(Object model)
	{
		super(model);
	}

	public void executeResult(ViewEngine viewEngine, ActionContext context)
	{
		context.getResponse().setContentType("application/json");
		try
		{
			Gson gsonParser = new Gson();
			context.getResponse().getWriter().write(gsonParser.toJson(getModel()));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
