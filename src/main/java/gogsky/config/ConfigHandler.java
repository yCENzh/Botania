package gogsky.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class ConfigHandler {
    
    private static Configuration config;
    
    // 配置选项 - 按照项目规范要求
    public static boolean enableSkybox = true;
    public static boolean enableInAllDimensions = false;
    
    public static void init(File configFile) {
        config = new Configuration(configFile);
        
        try {
            config.load();
            
            String desc = "Set this to false to disable the Garden of Glass skybox completely.";
            enableSkybox = loadPropBool("skybox.enabled", desc, enableSkybox);
            
            desc = "Set this to true to enable the skybox in all dimensions (default: only in overworld).";
            enableInAllDimensions = loadPropBool("skybox.allDimensions", desc, enableInAllDimensions);
            
        } catch (Exception e) {
            System.err.println("Error loading GogSkybox configuration!");
            e.printStackTrace();
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
    
    private static boolean loadPropBool(String propName, String desc, boolean defaultValue) {
        Property prop = config.get(Configuration.CATEGORY_GENERAL, propName, defaultValue);
        prop.comment = desc;
        return prop.getBoolean(defaultValue);
    }
}