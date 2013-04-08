package com.vgsoftware.vaction.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vgsoftware.vaction.ActionContext;
import com.vgsoftware.vaction.result.JsonResult;
import com.vgsoftware.vaction.result.ViewResult;

public abstract class Controller
{
	private ActionContext _context = null;
	private HttpServletRequest _request = null;
	private HttpServletResponse _response = null;
	private String[] _parameters = null;
	private Map<String, Object> _viewData = null;

	@SuppressWarnings("unused")
	private void init(ActionContext context)
	{
		_context = context;
		_request = context.getRequest();
		_response = context.getResponse();
		_parameters = context.getRouteData().getParameters();
		_viewData = context.getViewData();
	}

	protected ActionContext getContext()
	{
		return _context;
	}

	protected HttpServletRequest getRequest()
	{
		return _request;
	}

	protected HttpServletResponse getResponse()
	{
		return _response;
	}

	protected String[] getParameters()
	{
		return _parameters;
	}

	protected Map<String, Object> getViewData()
	{
		return _viewData;
	}

	protected ViewResult view()
	{
		return new ViewResult();
	}

	protected ViewResult view(String view)
	{
		return new ViewResult(view);
	}

	protected ViewResult view(Object model)
	{
		return new ViewResult(model);
	}

	protected ViewResult view(String view, Object model)
	{
		return new ViewResult(view, model);
	}

	protected JsonResult json(Object model)
	{
		return new JsonResult(model);
	}
}
