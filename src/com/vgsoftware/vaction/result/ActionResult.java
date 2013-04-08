package com.vgsoftware.vaction.result;

import java.io.IOException;

import com.vgsoftware.vaction.ActionContext;
import com.vgsoftware.vaction.view.ViewEngine;

public abstract class ActionResult
{
	private Object _model = null;
	private String _contentType = null;

	public ActionResult()
	{
	}

	public ActionResult(Object model)
	{
		_model = model;
	}

	public void setModel(Object model)
	{
		_model = model;
	}

	public Object getModel()
	{
		return _model;
	}

	public void setContentType(String contentType)
	{
		_contentType = contentType;
	}

	public String getContentType()
	{
		return _contentType;
	}

	public abstract void executeResult(ViewEngine viewEngine, ActionContext context)
			throws IOException;
}