package com.vgsoftware.vaction.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.vgsoftware.vaction.ActionContext;
import com.vgsoftware.vaction.ControllerManager;
import com.vgsoftware.vaction.VActionException;
import com.vgsoftware.vaction.controller.Controller;
import com.vgsoftware.vaction.result.ActionResult;
import com.vgsoftware.vaction.routing.RouteData;
import com.vgsoftware.vaction.view.VelocityViewEngine;

public class ActionServlet extends HttpServlet
{
	private VelocityViewEngine _viewEngine = null;
	private ControllerManager _controllerManager = null;

	@Override
	public void init()
		throws ServletException
	{
		super.init();

		_viewEngine = new VelocityViewEngine();
		_controllerManager = new ControllerManager();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		ActionContext context = new ActionContext();
		context.setServletContext(getServletContext());
		context.setRequest(request);
		context.setResponse(response);
		context.setRouteData(new RouteData(request.getPathInfo()));
		handleRequest(context);
	}

	private void handleRequest(ActionContext context)
		throws IOException
	{
		if (!StringUtils.isNotEmpty(context.getRouteData().getController()))
		{
			context.getResponse().sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		try
		{
			Controller controller = _controllerManager.initializeController(context);
			context.setController(controller);
			ActionResult result = _controllerManager.invoke(context);
			result.executeResult(_viewEngine, context);
		}
		catch (VActionException ex)
		{
			ex.printStackTrace();
			context.getResponse().sendError(500, ex.getMessage());
		}
	}
}