/*
 * Converted to 1.7.10 compatibility
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraftforge.client.IRenderHandler
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$RenderTickEvent
 */
package vazkii.skybox;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import vazkii.skybox.SkyblockSkyRenderer;

public class ModEventHandler {
    public static int ticksInGame = 0;
    public static float partialTicks = 0.0f;
    public static float delta = 0.0f;
    public static float total = 0.0f;

    private static void calcDelta() {
        float oldTotal = total;
        total = (float)ticksInGame + partialTicks;
        delta = total - oldTotal;
    }

    @SubscribeEvent
    public static void renderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            partialTicks = event.renderTickTime;
        }
    }

    @SubscribeEvent
    public static void clientTickEnd(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Minecraft mc = Minecraft.func_71410_x();
            GuiScreen gui = mc.field_71462_r;
            if (gui == null || !gui.func_73868_f()) {
                ++ticksInGame;
                partialTicks = 0.0f;
            }
            ModEventHandler.calcDelta();
        }
    }

    @SubscribeEvent
    public static void onRender(RenderWorldLastEvent event) {
        WorldClient world = Minecraft.func_71410_x().field_71441_e;
        if (world.field_73011_w.field_76574_g == 0 && !(world.field_73011_w.getSkyRenderer() instanceof SkyblockSkyRenderer)) {
            world.field_73011_w.setSkyRenderer((IRenderHandler)new SkyblockSkyRenderer());
        }
    }
}

