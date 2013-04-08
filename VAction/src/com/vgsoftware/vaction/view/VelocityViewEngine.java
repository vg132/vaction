package com.vgsoftware.vaction.view;

import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import com.vgsoftware.vaction.ActionContext;
import com.vgsoftware.vaction.routing.RouteData;

public class VelocityViewEngine extends ViewEngine
{
	private VelocityEngine engine = null;

	private void init(ActionContext context)
	{
		if (engine == null)
		{
			engine = new VelocityEngine();
			engine.setApplicationAttribute("javax.servlet.ServletContext", context.getServletContext());
			try
			{
				Properties properties = new Properties();
				properties.load(VelocityViewEngine.class.getClassLoader().getResourceAsStream("velocity.properties"));
				engine.init(properties);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private Template getTemplate(RouteData route)
	{
		Template template = null;
		for (String path : route.getViewPaths())
		{
			template = getTemplate(path);
			if (template != null)
			{
				break;
			}
		}
		return template;
	}

	private Template getTemplate(String template)
	{
		try
		{
			return engine.getTemplate(template);
		}
		catch (ResourceNotFoundException e)
		{
		}
		catch (ParseErrorException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void render(ActionContext context)
	{
		try
		{
			init(context);
			Template template = getTemplate(context.getRouteData());
			if (template != null)
			{
				VelocityContext velocityContext = new VelocityContext();
				for (String key : context.getViewData().keySet())
				{
					velocityContext.put(key, context.getViewData().get(key));
				}
				velocityContext.put("model", context.getModel());
				template.merge(velocityContext, context.getWriter());
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
