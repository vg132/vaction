package com.vgsoftware.vaction.routing;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.vgsoftware.vaction.Configuration;

public class RouteData
{
	private String _controller = null;
	private String _view = null;
	private String[] _parameters = null;

	public RouteData(final String pathInfo)
	{
		String[] parts = pathInfo.trim().replaceFirst("^/", "").replaceFirst("/$", "").split("/");
		_controller = StringUtils.capitalize(parts.length > 0 && StringUtils.isNotEmpty(parts[0]) ? parts[0] : Configuration.getDefaultController());
		_view = parts.length > 1 && StringUtils.isNotEmpty(parts[1]) ? parts[1] : Configuration.getDefaultView();

		if (parts.length > 2)
		{
			_parameters = new String[parts.length - 2];
			System.arraycopy(parts, 2, _parameters, 0, _parameters.length);
		}
	}

	public String getController()
	{
		return _controller;
	}

	public String getView()
	{
		return _view;
	}

	public void setView(String view)
	{
		_view = view;
	}

	public String[] getParameters()
	{
		return _parameters;
	}

	public List<String> getViewPaths()
	{
		List<String> paths = new ArrayList<String>();
		paths.add(getController() + "/" + getView() + "." + Configuration.getViewFileExtension());
		paths.add(Configuration.getViewFallbackFolder() + "/" + getView() + "." + Configuration.getViewFileExtension());
		return paths;
	}

	public String getControllerClass()
	{
		return Configuration.getControllerPackage() + "." + getController() + Configuration.getControllerPrefix();
	}
}
