package com.vgsoftware.vaction;

import java.lang.reflect.Method;

import com.vgsoftware.vaction.annotation.HttpGet;
import com.vgsoftware.vaction.annotation.HttpPost;
import com.vgsoftware.vaction.annotation.RequireHttps;
import com.vgsoftware.vaction.controller.Controller;
import com.vgsoftware.vaction.result.ActionResult;

public class ControllerManager
{
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Controller initializeController(ActionContext context)
			throws VActionException
	{
		try
		{
			Class controllerClass = Class.forName(context.getRouteData().getControllerClass());
			Controller instance = (Controller) controllerClass.newInstance();
			Method initMethod = Controller.class.getDeclaredMethod("init", ActionContext.class);
			initMethod.setAccessible(true);
			initMethod.invoke(instance, context);

			return instance;
		}
		catch (Exception ex)
		{
			VActionException exception = new VActionException("Unable to create instance of controller: " + context.getRouteData().getControllerClass());
			exception.addSuppressed(ex);
			throw exception;
		}
	}

	public ActionResult invoke(ActionContext context)
			throws VActionException
	{
		try
		{
			Method controllerMethod = context.getController().getClass().getMethod(context.getRouteData().getView());

			if (controllerMethod.isAnnotationPresent(RequireHttps.class) && !context.getRequest().isSecure())
			{
				throw new VActionException("Secure connection required");
			}
			if (context.getRequest().getMethod() == "GET" && controllerMethod.isAnnotationPresent(HttpPost.class) && !controllerMethod.isAnnotationPresent(HttpGet.class))
			{
				throw new VActionException("GET not supported");
			}
			if (context.getRequest().getMethod() == "POST" && controllerMethod.isAnnotationPresent(HttpGet.class) && !controllerMethod.isAnnotationPresent(HttpPost.class))
			{
				throw new VActionException("POST not supported");
			}
			ActionResult result = (ActionResult) controllerMethod.invoke(context.getController());
			context.setModel(result.getModel());

			return result;
		}
		catch (VActionException ex)
		{
			throw ex;
		}
		catch (Exception ex)
		{
			throw new VActionException("Unable to call action: " + context.getRouteData().getControllerClass() + "/" + context.getRouteData().getView(), ex);
		}
	}
}
