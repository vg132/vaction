package com.vgsoftware.vaction;

import java.util.Properties;

import com.vgsoftware.vaction.view.VelocityViewEngine;

public class Configuration
{
	private static String _controllerPackage = null;
	private static String _controllerPrefix = null;
	private static String _viewFallbackFolder = null;
	private static String _defaultView = null;
	private static String _viewFileExtension = null;
	private static String _defaultController = null;

	static
	{
		try
		{
			Properties properties = new Properties();
			properties.load(VelocityViewEngine.class.getClassLoader().getResourceAsStream("vaction.properties"));
			_controllerPackage = properties.getProperty("controller.package", null);
			if (_controllerPackage == null)
			{
				throw new Exception("controller.package property must be set in vaction.properties");
			}
			_controllerPrefix = properties.getProperty("controller.prefix", "Controller");
			_defaultController = properties.getProperty("controller.default", "Home");
			_defaultView = properties.getProperty("controller.default.view", "index");
			_viewFallbackFolder = properties.getProperty("view.fallback.folder", "shared");
			_viewFileExtension = properties.getProperty("view.file.extension", "vm");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static String getControllerPackage()
	{
		return _controllerPackage;
	}

	public static String getControllerPrefix()
	{
		return _controllerPrefix;
	}

	public static String getViewFallbackFolder()
	{
		return _viewFallbackFolder;
	}

	public static String getDefaultView()
	{
		return _defaultView;
	}

	public static String getViewFileExtension()
	{
		return _viewFileExtension;
	}

	public static String getDefaultController()
	{
		return _defaultController;
	}
}
