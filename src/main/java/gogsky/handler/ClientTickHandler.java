package gogsky.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class ClientTickHandler {
    
    public static int ticksInGame = 0;
    public static float partialTicks = 0;
    
    @SubscribeEvent
    public void renderTick(RenderTickEvent event) {
        if(event.phase == Phase.START)
            partialTicks = event.renderTickTime;
    }
    
    @SubscribeEvent
    public void clientTickEnd(ClientTickEvent event) {
        if(event.phase == Phase.END) {
            GuiScreen gui = Minecraft.getMinecraft().currentScreen;
            if(gui == null || !gui.doesGuiPauseGame()) {
                ticksInGame++;
                partialTicks = 0;
            }
        }
    }
}