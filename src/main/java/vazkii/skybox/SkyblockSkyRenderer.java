/*
 * Converted to 1.7.10 compatibility
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.WorldRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraftforge.client.IRenderHandler
 */
package vazkii.skybox;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.IRenderHandler;
import org.lwjgl.opengl.GL11;
import vazkii.skybox.ModEventHandler;
import vazkii.skybox.ModMethodHandles;

public class SkyblockSkyRenderer
extends IRenderHandler {
    private static final ResourceLocation textureSkybox = new ResourceLocation("gogskybox:textures/skybox.png");
    private static final ResourceLocation textureRainbow = new ResourceLocation("gogskybox:textures/rainbow.png");
    private static final ResourceLocation MOON_PHASES_TEXTURES = new ResourceLocation("textures/environment/moon_phases.png");
    private static final ResourceLocation SUN_TEXTURES = new ResourceLocation("textures/environment/sun.png");
    private static final ResourceLocation[] planetTextures = new ResourceLocation[]{new ResourceLocation("gogskybox:textures/planet0.png"), new ResourceLocation("gogskybox:textures/planet1.png"), new ResourceLocation("gogskybox:textures/planet2.png"), new ResourceLocation("gogskybox:textures/planet3.png"), new ResourceLocation("gogskybox:textures/planet4.png"), new ResourceLocation("gogskybox:textures/planet5.png")};

    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        int i;
        float celAng;
        int glSkyList;
        try {
            glSkyList = ModMethodHandles.glSkyList_getter.invokeExact(mc.field_71438_f);
        }
        catch (Throwable t) {
            return;
        }
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        Vec3 vec3 = world.func_72833_a(mc.func_175606_aa(), partialTicks);
        float f = (float)vec3.field_72450_a;
        float f1 = (float)vec3.field_72448_b;
        float f2 = (float)vec3.field_72449_c;
        float insideVoid = 0.0f;
        if (mc.field_71439_g.field_70163_u <= -2.0) {
            insideVoid = (float)Math.min(1.0, -(mc.field_71439_g.field_70163_u + 2.0) / 30.0);
        }
        f = Math.max(0.0f, f - insideVoid);
        f1 = Math.max(0.0f, f1 - insideVoid);
        f2 = Math.max(0.0f, f2 - insideVoid);
        GL11.glColor3f(f, f1, f2);
        Tessellator tessellator = Tessellator.field_78398_a;
        WorldRenderer worldrenderer = tessellator.func_178180_c();
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glColor3f(f, f1, f2);
        GL11.glCallList(glSkyList);
        GL11.glDisable(GL11.GL_FOG);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.func_148821_a(770, 771, 1, 0);
        RenderHelper.func_74518_a();
        float[] afloat = world.field_73011_w.func_76560_a(world.func_72826_c(partialTicks), partialTicks);
        if (afloat != null) {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glShadeModel(GL11.GL_SMOOTH);
            GL11.glPushMatrix();
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(MathHelper.func_76126_a(world.func_72929_e(partialTicks)) < 0.0f ? 180.0f : 0.0f, 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
            float f6 = afloat[0];
            float f7 = afloat[1];
            float f8 = afloat[2];
            worldrenderer.func_178382_a(6);
            worldrenderer.func_178974_a(0.0, 100.0, 0.0);
            worldrenderer.func_178982_a(f6, f7, f8, afloat[3] * (1.0f - insideVoid));
            worldrenderer.func_178977_d();
            for (int l = 0; l <= 16; ++l) {
                float f21 = (float)l * ((float)Math.PI * 2) / 16.0f;
                float f12 = MathHelper.func_76126_a(f21);
                float f13 = MathHelper.func_76134_b(f21);
                worldrenderer.func_178974_a((double)(f12 * 120.0f), (double)(f13 * 120.0f), (double)(-f13 * 40.0f * afloat[3]));
                worldrenderer.func_178982_a(afloat[0], afloat[1], afloat[2], 0.0f);
                worldrenderer.func_178977_d();
            }
            tessellator.func_78381_a();
            GL11.glPopMatrix();
            GL11.glShadeModel(GL11.GL_FLAT);
        }
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.func_148821_a(770, 1, 1, 0);
        GL11.glPushMatrix();
        float f16 = 1.0f - world.func_72867_j(partialTicks);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, f16);
        GL11.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f);
        float effCelAng = celAng = world.func_72826_c(partialTicks);
        if ((double)celAng > 0.5) {
            effCelAng = 0.5f - (celAng - 0.5f);
        }
        float f17 = 20.0f;
        float lowA = Math.max(0.0f, effCelAng - 0.3f) * f16;
        float a = Math.max(0.1f, lowA);
        OpenGlHelper.func_148821_a(770, 771, 1, 0);
        GL11.glPushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, a * 4.0f * (1.0f - insideVoid));
        GL11.glRotatef(90.0f, 0.5f, 0.5f, 0.0f);
        block14: for (int p = 0; p < planetTextures.length; ++p) {
            mc.field_71446_o.func_110577_a(planetTextures[p]);
            worldrenderer.func_178382_a(7);
            worldrenderer.func_178374_a((double)(-f17), 100.0, (double)(-f17), 0.0, 0.0);
            worldrenderer.func_178374_a((double)f17, 100.0, (double)(-f17), 1.0, 0.0);
            worldrenderer.func_178374_a((double)f17, 100.0, (double)f17, 1.0, 1.0);
            worldrenderer.func_178374_a((double)(-f17), 100.0, (double)f17, 0.0, 1.0);
            tessellator.func_78381_a();
            switch (p) {
                case 0: {
                    GL11.glRotatef(70.0f, 1.0f, 0.0f, 0.0f);
                    f17 = 12.0f;
                    continue block14;
                }
                case 1: {
                    GL11.glRotatef(120.0f, 0.0f, 0.0f, 1.0f);
                    f17 = 15.0f;
                    continue block14;
                }
                case 2: {
                    GL11.glRotatef(80.0f, 1.0f, 0.0f, 1.0f);
                    f17 = 25.0f;
                    continue block14;
                }
                case 3: {
                    GL11.glRotatef(100.0f, 0.0f, 0.0f, 1.0f);
                    f17 = 10.0f;
                    continue block14;
                }
                case 4: {
                    GL11.glRotatef(-60.0f, 1.0f, 0.0f, 0.5f);
                    f17 = 40.0f;
                }
            }
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
        mc.field_71446_o.func_110577_a(textureSkybox);
        f17 = 20.0f;
        a = lowA;
        GL11.glPushMatrix();
        OpenGlHelper.func_148821_a(770, 1, 1, 0);
        GL11.glTranslatef(0.0f, -1.0f, 0.0f);
        GL11.glRotatef(220.0f, 1.0f, 0.0f, 0.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, a);
        int angles = 90;
        float y = 2.0f;
        float y0 = 0.0f;
        float uPer = 0.0027777778f;
        float anglePer = 360.0f / (float)angles;
        double fuzzPer = Math.PI * 10 / (double)angles;
        float rotSpeed = 1.0f;
        float rotSpeedMod = 0.4f;
        block15: for (int p = 0; p < 3; ++p) {
            float baseAngle = rotSpeed * rotSpeedMod * ((float)ModEventHandler.ticksInGame + ModEventHandler.partialTicks);
            GL11.glRotatef(((float)ModEventHandler.ticksInGame + ModEventHandler.partialTicks) * 0.25f * rotSpeed * rotSpeedMod, 0.0f, 1.0f, 0.0f);
            worldrenderer.func_178382_a(7);
            for (int i2 = 0; i2 < angles; ++i2) {
                int j = i2;
                if (i2 % 2 == 0) {
                    --j;
                }
                float ang = (float)j * anglePer + baseAngle;
                double xp = Math.cos((double)ang * Math.PI / 180.0) * (double)f17;
                double zp = Math.sin((double)ang * Math.PI / 180.0) * (double)f17;
                double yo = Math.sin(fuzzPer * (double)j) * 1.0;
                float ut = ang * uPer;
                if (i2 % 2 == 0) {
                    worldrenderer.func_178374_a(xp, yo + (double)y0 + (double)y, zp, (double)ut, 1.0);
                    worldrenderer.func_178374_a(xp, yo + (double)y0, zp, (double)ut, 0.0);
                    continue;
                }
                worldrenderer.func_178374_a(xp, yo + (double)y0, zp, (double)ut, 0.0);
                worldrenderer.func_178374_a(xp, yo + (double)y0 + (double)y, zp, (double)ut, 1.0);
            }
            tessellator.func_78381_a();
            switch (p) {
                case 0: {
                    GL11.glRotatef(20.0f, 1.0f, 0.0f, 0.0f);
                    GL11.glColor4f(1.0f, 0.4f, 0.4f, a);
                    fuzzPer = 43.982297150257104 / (double)angles;
                    rotSpeed = 0.2f;
                    continue block15;
                }
                case 1: {
                    GL11.glRotatef(50.0f, 1.0f, 0.0f, 0.0f);
                    GL11.glColor4f(0.4f, 1.0f, 0.7f, a);
                    fuzzPer = Math.PI * 6 / (double)angles;
                    rotSpeed = 2.0f;
                }
            }
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        OpenGlHelper.func_148821_a(770, 771, 1, 0);
        mc.field_71446_o.func_110577_a(textureRainbow);
        f17 = 10.0f;
        float effCelAng1 = celAng;
        if (effCelAng1 > 0.25f) {
            effCelAng1 = 1.0f - effCelAng1;
        }
        effCelAng1 = 0.25f - Math.min(0.25f, effCelAng1);
        long time = world.func_72820_D() + 1000L;
        int day = (int)(time / 24000L);
        Random rand = new Random(day * 255);
        float angle1 = rand.nextFloat() * 360.0f;
        float angle2 = rand.nextFloat() * 360.0f;
        GL11.glColor4f(1.0f, 1.0f, 1.0f, effCelAng1 * (1.0f - insideVoid));
        GL11.glRotatef(angle1, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(angle2, 0.0f, 0.0f, 1.0f);
        worldrenderer.func_178382_a(7);
        for (i = 0; i < angles; ++i) {
            int j = i;
            if (i % 2 == 0) {
                --j;
            }
            float ang = (float)j * anglePer;
            double xp = Math.cos((double)ang * Math.PI / 180.0) * (double)f17;
            double zp = Math.sin((double)ang * Math.PI / 180.0) * (double)f17;
            double yo = 0.0;
            float ut = ang * uPer;
            if (i % 2 == 0) {
                worldrenderer.func_178374_a(xp, yo + (double)y0 + (double)y, zp, (double)ut, 1.0);
                worldrenderer.func_178374_a(xp, yo + (double)y0, zp, (double)ut, 0.0);
                continue;
            }
            worldrenderer.func_178374_a(xp, yo + (double)y0, zp, (double)ut, 0.0);
            worldrenderer.func_178374_a(xp, yo + (double)y0 + (double)y, zp, (double)ut, 1.0);
        }
        tessellator.func_78381_a();
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f - insideVoid);
        OpenGlHelper.func_148821_a(770, 1, 1, 0);
        GL11.glRotatef(world.func_72826_c(partialTicks) * 360.0f, 1.0f, 0.0f, 0.0f);
        f17 = 60.0f;
        mc.field_71446_o.func_110577_a(SUN_TEXTURES);
        worldrenderer.func_178382_a(7);
        worldrenderer.func_178374_a((double)(-f17), 100.0, (double)(-f17), 0.0, 0.0);
        worldrenderer.func_178374_a((double)f17, 100.0, (double)(-f17), 1.0, 0.0);
        worldrenderer.func_178374_a((double)f17, 100.0, (double)f17, 1.0, 1.0);
        worldrenderer.func_178374_a((double)(-f17), 100.0, (double)f17, 0.0, 1.0);
        tessellator.func_78381_a();
        f17 = 60.0f;
        mc.field_71446_o.func_110577_a(MOON_PHASES_TEXTURES);
        i = world.func_72853_d();
        int k = i % 4;
        int i1 = i / 4 % 2;
        float f22 = (float)(k + 0) / 4.0f;
        float f23 = (float)(i1 + 0) / 2.0f;
        float f24 = (float)(k + 1) / 4.0f;
        float f14 = (float)(i1 + 1) / 2.0f;
        worldrenderer.func_178382_a(7);
        worldrenderer.func_178374_a((double)(-f17), -100.0, (double)f17, (double)f24, (double)f14);
        worldrenderer.func_178374_a((double)f17, -100.0, (double)f17, (double)f22, (double)f14);
        worldrenderer.func_178374_a((double)f17, -100.0, (double)(-f17), (double)f22, (double)f23);
        worldrenderer.func_178374_a((double)(-f17), -100.0, (double)(-f17), (double)f24, (double)f23);
        tessellator.func_78381_a();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        this.renderStars(mc, f16 *= Math.max(0.1f, effCelAng * 2.0f), partialTicks);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(true);
    }

    private void renderStars(Minecraft mc, float alpha, float partialTicks) {
        int starGLCallList;
        try {
            starGLCallList = ModMethodHandles.starGLCallList_getter.invokeExact(mc.field_71438_f);
        }
        catch (Throwable t) {
            return;
        }
        float t = ((float)ModEventHandler.ticksInGame + partialTicks + 2000.0f) * 0.005f;
        GL11.glPushMatrix();
        GL11.glPushMatrix();
        GL11.glRotatef(t * 3.0f, 0.0f, 1.0f, 0.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, alpha);
        GL11.glCallList(starGLCallList);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glRotatef(t, 0.0f, 1.0f, 0.0f);
        GL11.glColor4f(0.5f, 1.0f, 1.0f, alpha);
        GL11.glCallList(starGLCallList);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glRotatef(t * 2.0f, 0.0f, 1.0f, 0.0f);
        GL11.glColor4f(1.0f, 0.75f, 0.75f, alpha);
        GL11.glCallList(starGLCallList);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glRotatef(t * 3.0f, 0.0f, 0.0f, 1.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.25f * alpha);
        GL11.glCallList(starGLCallList);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glRotatef(t, 0.0f, 0.0f, 1.0f);
        GL11.glColor4f(0.5f, 1.0f, 1.0f, 0.25f * alpha);
        GL11.glCallList(starGLCallList);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glRotatef(t * 2.0f, 0.0f, 0.0f, 1.0f);
        GL11.glColor4f(1.0f, 0.75f, 0.75f, 0.25f * alpha);
        GL11.glCallList(starGLCallList);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}

