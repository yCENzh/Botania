/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 */
package vazkii.skybox;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import vazkii.skybox.ModEventHandler;

@Mod(modid="gogskybox", name="Garden of Glass Skybox", version="1.1-3", dependencies="", clientSideOnly=true, acceptedMinecraftVersions="[1.7.10]")
public class GoGSkybox {
    public static final String MOD_ID = "gogskybox";
    public static final String MOD_NAME = "Garden of Glass Skybox";
    public static final String BUILD = "3";
    public static final String VERSION = "1.1-3";
    public static final String DEPENDENCIES = "";
    public static final String PREFIX_MOD = "gogskybox:";
    public static final String PREFIX_TEXTURES = "gogskybox:textures/";
    public static final String MISC_SKYBOX = "gogskybox:textures/skybox.png";
    public static final String MISC_RAINBOW = "gogskybox:textures/rainbow.png";
    public static final String MISC_PLANET = "gogskybox:textures/planet";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(ModEventHandler.class);
    }
}

