package gogsky.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import gogsky.proxy.CommonProxy;

@Mod(modid = GogSkybox.MOD_ID, name = GogSkybox.MOD_NAME, version = GogSkybox.VERSION)
public class GogSkybox {
    
    public static final String MOD_ID = "gogskybox";
    public static final String MOD_NAME = "Garden of Glass Skybox";
    public static final String VERSION = "@VERSION@";
    
    @Mod.Instance(MOD_ID)
    public static GogSkybox instance;
    
    @SidedProxy(clientSide = "gogsky.proxy.ClientProxy", serverSide = "gogsky.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}