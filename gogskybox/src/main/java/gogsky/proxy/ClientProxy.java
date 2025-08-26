package gogsky.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import gogsky.handler.ClientTickHandler;
import gogsky.handler.SkyboxRenderHandler;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		
		MinecraftForge.EVENT_BUS.register(new SkyboxRenderHandler());
		FMLCommonHandler.instance().bus().register(new ClientTickHandler());
	}
}