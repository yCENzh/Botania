package gogsky.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {
	
	private static Configuration config;
	
	public static boolean enableSkybox = true;
	public static boolean enableInAllDimensions = false;
	
	public static void init(File configFile) {
		config = new Configuration(configFile);
		
		config.load();
		
		String desc;
		
		desc = "Set this to false to disable the fancy skybox.";
		enableSkybox = loadPropBool("skybox.enabled", desc, enableSkybox);
		
		desc = "Set this to true to enable the fancy skybox in all dimensions. If false, only works in the overworld (dimension 0).";
		enableInAllDimensions = loadPropBool("skybox.allDimensions", desc, enableInAllDimensions);
		
		if (config.hasChanged()) {
			config.save();
		}
	}
	
	private static boolean loadPropBool(String propName, String desc, boolean defaultValue) {
		return config.get("general", propName, defaultValue, desc).getBoolean(defaultValue);
	}
}