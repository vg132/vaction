/**
 * Code copied from http://jvdkamp.wordpress.com/2010/02/12/combining-gae-apache-velocity-and-jquery/
 */
package com.vgsoftware.vaction.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Vector;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

public class VelocityResourceLoader extends ResourceLoader
{
	private Vector<String> paths = null;

	@Override
	public long getLastModified(Resource resource)
	{
		return resource.getLastModified();
	}

	@Override
	public InputStream getResourceStream(String template)
		throws ResourceNotFoundException
	{
		int size = paths.size();
		for (int i = 0; i < size; i++)
		{
			String path = paths.get(i);

			InputStream is = null;
			try
			{
				is = new FileInputStream(path + "/" + template);
				return is;
			}
			catch (FileNotFoundException e)
			{
			}
		}
		throw new ResourceNotFoundException(template);
	}

	@Override
	public void init(ExtendedProperties configuration)
	{
		paths = configuration.getVector("path");
	}

	@Override
	public boolean isSourceModified(Resource resource)
	{
		return false;
	}
}
