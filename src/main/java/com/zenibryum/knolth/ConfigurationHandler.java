package com.zenibryum.knolth;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class ConfigurationHandler {
	/*public static void init(File configFile)
	{
		// Create the configuration object from the given configFile
		Configuration configuration = new Configuration(configFile);
		
		boolean configValue = false;
		
		try
		{
			// Load the config file
			configuration.load();
			
			// Read in proprieties from config file
			configValue = configuration.get( Configuration.CATEGORY_GENERAL, "configValue", true, "This is an example config value").getBoolean(true);
		}
		catch(Exception e)
		{
			// Log the exception
		}
		finally
		{
			// Save the config file
			configuration.save();
		}
		
		//System.out.println(configValue);
	}*/
	public static Configuration configuration;
	public static boolean testValue = false;
	
	public static void init(File configFile)
	{
		// Create the configuration object from the given configuration file
		if(configuration == null)
		{
			configuration = new Configuration(configFile);
			loadConfiguration();
		}
	}
	
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.modID.equalsIgnoreCase (Reference.MOD_ID))
		{
			loadConfiguration();
		}
	}
	
	private static void loadConfiguration()
	{
		testValue = configuration.getBoolean("configValue", Configuration.CATEGORY_GENERAL, false, "This example was put here by Tony");
	}
}
