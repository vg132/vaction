package com.vgsoftware.vaction;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vgsoftware.vaction.controller.Controller;
import com.vgsoftware.vaction.routing.RouteData;

public class ActionContext
{
	private Object _model = null;
	private Map<String, Object> _viewData = null;
	private RouteData _routeData = null;
	private HttpServletRequest _request = null;
	private HttpServletResponse _response = null;
	private StringWriter _writer = null;
	private Controller _controller = null;
	private ServletContext _servletContext = null;

	public ActionContext()
	{
		_viewData = new HashMap<String, Object>();
		_writer = new StringWriter();
	}

	public Object getModel()
	{
		return _model;
	}

	public void setModel(Object model)
	{
		_model = model;
	}

	public Map<String, Object> getViewData()
	{
		return _viewData;
	}

	public void setViewData(Map<String, Object> viewData)
	{
		_viewData = viewData;
	}

	public RouteData getRouteData()
	{
		return _routeData;
	}

	public void setRouteData(RouteData routeData)
	{
		_routeData = routeData;
	}

	public HttpServletRequest getRequest()
	{
		return _request;
	}

	public void setRequest(HttpServletRequest request)
	{
		_request = request;
	}

	public HttpServletResponse getResponse()
	{
		return _response;
	}

	public void setResponse(HttpServletResponse response)
	{
		_response = response;
	}

	public StringWriter getWriter()
	{
		return _writer;
	}

	public void setWriter(StringWriter writer)
	{
		_writer = writer;
	}

	public void setController(Controller controller)
	{
		_controller = controller;
	}

	public Controller getController()
	{
		return _controller;
	}

	public void setServletContext(ServletContext servletContext)
	{
		_servletContext = servletContext;
	}

	public ServletContext getServletContext()
	{
		return _servletContext;
	}
}
