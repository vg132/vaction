package com.vgsoftware.vaction.result;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import com.vgsoftware.vaction.ActionContext;
import com.vgsoftware.vaction.Configuration;
import com.vgsoftware.vaction.view.ViewEngine;

public class RedirectResult extends ActionResult
{
	private String _controller = null;
	private String _view = null;
	private String _url = null;

	public RedirectResult()
	{
		super();
	}

	public RedirectResult(String url)
	{
		super();
		_url = url;
	}

	public RedirectResult(String view, String controller)
	{
		super();
		_view = view;
		_controller = controller;
	}

	public String getUrl()
	{
		return _url;
	}

	public void setUrl(String url)
	{
		_url = url;
	}

	public String getController()
	{
		return _controller;
	}

	public void setController(String controller)
	{
		_controller = controller;
	}

	public String getView()
	{
		return _view;
	}

	public void setView(String view)
	{
		_view = view;
	}

	@Override
	public void executeResult(ViewEngine viewEngine, ActionContext context)
			throws IOException
	{
		if (getUrl() != null && !getUrl().equals(""))
		{
			context.getResponse().sendRedirect(getUrl());
		}
		String controller = StringUtils.isNotEmpty(getController()) ? getController() : Configuration.getDefaultController();
		String view = StringUtils.isNotEmpty(getView()) ? getView() : Configuration.getDefaultView();
		String url = context.getRequest().getContextPath().replaceFirst("/$", "");
		context.getResponse().sendRedirect(url + "/" + controller + "/" + view);
	}
}
