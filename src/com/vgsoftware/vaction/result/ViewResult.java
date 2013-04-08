package com.vgsoftware.vaction.result;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import com.vgsoftware.vaction.ActionContext;
import com.vgsoftware.vaction.view.ViewEngine;

public class ViewResult extends ActionResult
{
	private String _view = null;

	public ViewResult()
	{
		super();
	}

	public ViewResult(Object model)
	{
		super(model);
	}

	public ViewResult(String view)
	{
		super();
		_view = view;
	}

	public ViewResult(String view, Object model)
	{
		super(model);
		_view = view;
	}

	public String getView()
	{
		return _view;
	}

	public void setView(String view)
	{
		_view = view;
	}

	public void executeResult(ViewEngine viewEngine, ActionContext context)
			throws IOException
	{
		if (StringUtils.isNotEmpty(_view))
		{
			context.getRouteData().setView(_view);
		}
		viewEngine.render(context);
		if (StringUtils.isNotEmpty(getContentType()))
		{
			context.getResponse().setContentType(getContentType());
		}
		else
		{
			context.getResponse().setContentType("text/html");
		}
		context.getResponse().getWriter().write(context.getWriter().toString());
	}
}
