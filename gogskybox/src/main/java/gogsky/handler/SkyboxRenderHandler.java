package gogsky.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import gogsky.config.ConfigHandler;
import gogsky.render.GogSkyRenderer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SkyboxRenderHandler {

	@SubscribeEvent
	public void onRenderWorldLast(RenderWorldLastEvent event) {
		World world = Minecraft.getMinecraft().theWorld;
		if (world != null && ConfigHandler.enableSkybox) {
			boolean shouldEnable = ConfigHandler.enableInAllDimensions || world.provider.dimensionId == 0;
			
			if (shouldEnable) {
				if (!(world.provider.getSkyRenderer() instanceof GogSkyRenderer)) {
					world.provider.setSkyRenderer(new GogSkyRenderer());
				}
			}
		}
	}
}