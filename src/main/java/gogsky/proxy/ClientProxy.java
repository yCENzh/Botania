package gogsky.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import gogsky.handler.ClientTickHandler;
import gogsky.handler.SkyboxRenderHandler;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }
    
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        
        // 注册事件处理器
        MinecraftForge.EVENT_BUS.register(new SkyboxRenderHandler());
        FMLCommonHandler.instance().bus().register(new ClientTickHandler());
    }
    
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}