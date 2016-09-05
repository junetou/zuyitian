package org.adny.work.property;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 *
 *developer
 *2015年3月25日上午10:06:27
 *
 */
public class SystemProperiesConfigure extends PropertyPlaceholderConfigurer
{
	private static Properties properties;
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException
	{
		super.processProperties(beanFactoryToProcess, props);
		properties = props;
	}
	
	public static String get(String key)
	{
		return (String) properties.get(key);
	}
}
