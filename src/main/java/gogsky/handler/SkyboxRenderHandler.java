package gogsky.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import gogsky.config.ConfigHandler;
import gogsky.render.GogSkyRenderer;

public class SkyboxRenderHandler {

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        World world = Minecraft.getMinecraft().theWorld;
        
        if (world == null || !ConfigHandler.enableSkybox) {
            return;
        }
        
        // 检查是否应该在此维度启用
        boolean shouldEnable = false;
        if (ConfigHandler.enableInAllDimensions) {
            shouldEnable = true;
        } else {
            // 默认只在主世界启用（维度ID为0）
            shouldEnable = world.provider.dimensionId == 0;
        }
        
        if (shouldEnable) {
            if (!(world.provider.getSkyRenderer() instanceof GogSkyRenderer)) {
                world.provider.setSkyRenderer(new GogSkyRenderer());
            }
        }
    }
}