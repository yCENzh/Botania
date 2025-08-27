/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.renderer.vertex.VertexBuffer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.client.IRenderHandler
 */
package vazkii.skybox;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.IRenderHandler;
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
        VertexBuffer skyVBO;
        int glSkyList;
        try {
            glSkyList = ModMethodHandles.glSkyList_getter.invokeExact(mc.field_71438_f);
            skyVBO = ModMethodHandles.skyVBO_getter.invokeExact(mc.field_71438_f);
        }
        catch (Throwable t) {
            return;
        }
        GlStateManager.func_179090_x();
        Vec3d vec3d = world.func_72833_a(mc.func_175606_aa(), partialTicks);
        float f = (float)vec3d.field_72450_a;
        float f1 = (float)vec3d.field_72448_b;
        float f2 = (float)vec3d.field_72449_c;
        float insideVoid = 0.0f;
        if (mc.field_71439_g.field_70163_u <= -2.0) {
            insideVoid = (float)Math.min(1.0, -(mc.field_71439_g.field_70163_u + 2.0) / 30.0);
        }
        f = Math.max(0.0f, f - insideVoid);
        f1 = Math.max(0.0f, f1 - insideVoid);
        f2 = Math.max(0.0f, f2 - insideVoid);
        GlStateManager.func_179124_c((float)f, (float)f1, (float)f2);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder vertexbuffer = tessellator.func_178180_c();
        GlStateManager.func_179132_a((boolean)false);
        GlStateManager.func_179127_m();
        GlStateManager.func_179124_c((float)f, (float)f1, (float)f2);
        if (OpenGlHelper.func_176075_f()) {
            skyVBO.func_177359_a();
            GlStateManager.func_187410_q((int)32884);
            GlStateManager.func_187420_d((int)3, (int)5126, (int)12, (int)0);
            skyVBO.func_177358_a(7);
            skyVBO.func_177361_b();
            GlStateManager.func_187429_p((int)32884);
        } else {
            GlStateManager.func_179148_o((int)glSkyList);
        }
        GlStateManager.func_179106_n();
        GlStateManager.func_179118_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        RenderHelper.func_74518_a();
        float[] afloat = world.field_73011_w.func_76560_a(world.func_72826_c(partialTicks), partialTicks);
        if (afloat != null) {
            GlStateManager.func_179090_x();
            GlStateManager.func_179103_j((int)7425);
            GlStateManager.func_179094_E();
            GlStateManager.func_179114_b((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.func_179114_b((float)(MathHelper.func_76126_a((float)world.func_72929_e(partialTicks)) < 0.0f ? 180.0f : 0.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GlStateManager.func_179114_b((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            float f6 = afloat[0];
            float f7 = afloat[1];
            float f8 = afloat[2];
            vertexbuffer.func_181668_a(6, DefaultVertexFormats.field_181706_f);
            vertexbuffer.func_181662_b(0.0, 100.0, 0.0).func_181666_a(f6, f7, f8, afloat[3] * (1.0f - insideVoid)).func_181675_d();
            for (int l = 0; l <= 16; ++l) {
                float f21 = (float)l * ((float)Math.PI * 2) / 16.0f;
                float f12 = MathHelper.func_76126_a((float)f21);
                float f13 = MathHelper.func_76134_b((float)f21);
                vertexbuffer.func_181662_b((double)(f12 * 120.0f), (double)(f13 * 120.0f), (double)(-f13 * 40.0f * afloat[3])).func_181666_a(afloat[0], afloat[1], afloat[2], 0.0f).func_181675_d();
            }
            tessellator.func_78381_a();
            GlStateManager.func_179121_F();
            GlStateManager.func_179103_j((int)7424);
        }
        GlStateManager.func_179098_w();
        GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.func_179094_E();
        float f16 = 1.0f - world.func_72867_j(partialTicks);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)f16);
        GlStateManager.func_179114_b((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        float effCelAng = celAng = world.func_72826_c(partialTicks);
        if ((double)celAng > 0.5) {
            effCelAng = 0.5f - (celAng - 0.5f);
        }
        float f17 = 20.0f;
        float lowA = Math.max(0.0f, effCelAng - 0.3f) * f16;
        float a = Math.max(0.1f, lowA);
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
        GlStateManager.func_179094_E();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)(a * 4.0f * (1.0f - insideVoid)));
        GlStateManager.func_179114_b((float)90.0f, (float)0.5f, (float)0.5f, (float)0.0f);
        block14: for (int p = 0; p < planetTextures.length; ++p) {
            mc.field_71446_o.func_110577_a(planetTextures[p]);
            tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181707_g);
            tessellator.func_178180_c().func_181662_b((double)(-f17), 100.0, (double)(-f17)).func_187315_a(0.0, 0.0).func_181675_d();
            tessellator.func_178180_c().func_181662_b((double)f17, 100.0, (double)(-f17)).func_187315_a(1.0, 0.0).func_181675_d();
            tessellator.func_178180_c().func_181662_b((double)f17, 100.0, (double)f17).func_187315_a(1.0, 1.0).func_181675_d();
            tessellator.func_178180_c().func_181662_b((double)(-f17), 100.0, (double)f17).func_187315_a(0.0, 1.0).func_181675_d();
            tessellator.func_78381_a();
            switch (p) {
                case 0: {
                    GlStateManager.func_179114_b((float)70.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    f17 = 12.0f;
                    continue block14;
                }
                case 1: {
                    GlStateManager.func_179114_b((float)120.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    f17 = 15.0f;
                    continue block14;
                }
                case 2: {
                    GlStateManager.func_179114_b((float)80.0f, (float)1.0f, (float)0.0f, (float)1.0f);
                    f17 = 25.0f;
                    continue block14;
                }
                case 3: {
                    GlStateManager.func_179114_b((float)100.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    f17 = 10.0f;
                    continue block14;
                }
                case 4: {
                    GlStateManager.func_179114_b((float)-60.0f, (float)1.0f, (float)0.0f, (float)0.5f);
                    f17 = 40.0f;
                }
            }
        }
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179121_F();
        mc.field_71446_o.func_110577_a(textureSkybox);
        f17 = 20.0f;
        a = lowA;
        GlStateManager.func_179094_E();
        GlStateManager.func_179120_a((int)770, (int)1, (int)1, (int)0);
        GlStateManager.func_179109_b((float)0.0f, (float)-1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)220.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)a);
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
            GlStateManager.func_179114_b((float)(((float)ModEventHandler.ticksInGame + ModEventHandler.partialTicks) * 0.25f * rotSpeed * rotSpeedMod), (float)0.0f, (float)1.0f, (float)0.0f);
            tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181707_g);
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
                    tessellator.func_178180_c().func_181662_b(xp, yo + (double)y0 + (double)y, zp).func_187315_a((double)ut, 1.0).func_181675_d();
                    tessellator.func_178180_c().func_181662_b(xp, yo + (double)y0, zp).func_187315_a((double)ut, 0.0).func_181675_d();
                    continue;
                }
                tessellator.func_178180_c().func_181662_b(xp, yo + (double)y0, zp).func_187315_a((double)ut, 0.0).func_181675_d();
                tessellator.func_178180_c().func_181662_b(xp, yo + (double)y0 + (double)y, zp).func_187315_a((double)ut, 1.0).func_181675_d();
            }
            tessellator.func_78381_a();
            switch (p) {
                case 0: {
                    GlStateManager.func_179114_b((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.func_179131_c((float)1.0f, (float)0.4f, (float)0.4f, (float)a);
                    fuzzPer = 43.982297150257104 / (double)angles;
                    rotSpeed = 0.2f;
                    continue block15;
                }
                case 1: {
                    GlStateManager.func_179114_b((float)50.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.func_179131_c((float)0.4f, (float)1.0f, (float)0.7f, (float)a);
                    fuzzPer = Math.PI * 6 / (double)angles;
                    rotSpeed = 2.0f;
                }
            }
        }
        GlStateManager.func_179121_F();
        GlStateManager.func_179094_E();
        GlStateManager.func_179120_a((int)770, (int)771, (int)1, (int)0);
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
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)(effCelAng1 * (1.0f - insideVoid)));
        GlStateManager.func_179114_b((float)angle1, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)angle2, (float)0.0f, (float)0.0f, (float)1.0f);
        tessellator.func_178180_c().func_181668_a(7, DefaultVertexFormats.field_181707_g);
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
                tessellator.func_178180_c().func_181662_b(xp, yo + (double)y0 + (double)y, zp).func_187315_a((double)ut, 1.0).func_181675_d();
                tessellator.func_178180_c().func_181662_b(xp, yo + (double)y0, zp).func_187315_a((double)ut, 0.0).func_181675_d();
                continue;
            }
            tessellator.func_178180_c().func_181662_b(xp, yo + (double)y0, zp).func_187315_a((double)ut, 0.0).func_181675_d();
            tessellator.func_178180_c().func_181662_b(xp, yo + (double)y0 + (double)y, zp).func_187315_a((double)ut, 1.0).func_181675_d();
        }
        tessellator.func_78381_a();
        GlStateManager.func_179121_F();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)(1.0f - insideVoid));
        GlStateManager.func_179120_a((int)770, (int)1, (int)1, (int)0);
        GlStateManager.func_179114_b((float)(world.func_72826_c(partialTicks) * 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        f17 = 60.0f;
        mc.field_71446_o.func_110577_a(SUN_TEXTURES);
        vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        vertexbuffer.func_181662_b((double)(-f17), 100.0, (double)(-f17)).func_187315_a(0.0, 0.0).func_181675_d();
        vertexbuffer.func_181662_b((double)f17, 100.0, (double)(-f17)).func_187315_a(1.0, 0.0).func_181675_d();
        vertexbuffer.func_181662_b((double)f17, 100.0, (double)f17).func_187315_a(1.0, 1.0).func_181675_d();
        vertexbuffer.func_181662_b((double)(-f17), 100.0, (double)f17).func_187315_a(0.0, 1.0).func_181675_d();
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
        vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        vertexbuffer.func_181662_b((double)(-f17), -100.0, (double)f17).func_187315_a((double)f24, (double)f14).func_181675_d();
        vertexbuffer.func_181662_b((double)f17, -100.0, (double)f17).func_187315_a((double)f22, (double)f14).func_181675_d();
        vertexbuffer.func_181662_b((double)f17, -100.0, (double)(-f17)).func_187315_a((double)f22, (double)f23).func_181675_d();
        vertexbuffer.func_181662_b((double)(-f17), -100.0, (double)(-f17)).func_187315_a((double)f24, (double)f23).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179090_x();
        this.renderStars(mc, f16 *= Math.max(0.1f, effCelAng * 2.0f), partialTicks);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179084_k();
        GlStateManager.func_179141_d();
        GlStateManager.func_179127_m();
        GlStateManager.func_179121_F();
        GlStateManager.func_179098_w();
        GlStateManager.func_179132_a((boolean)true);
    }

    private void renderStars(Minecraft mc, float alpha, float partialTicks) {
        VertexBuffer starVBO;
        int starGLCallList;
        try {
            starGLCallList = ModMethodHandles.starGLCallList_getter.invokeExact(mc.field_71438_f);
            starVBO = ModMethodHandles.starVBO_getter.invokeExact(mc.field_71438_f);
        }
        catch (Throwable t) {
            return;
        }
        float t = ((float)ModEventHandler.ticksInGame + partialTicks + 2000.0f) * 0.005f;
        GlStateManager.func_179094_E();
        GlStateManager.func_179094_E();
        GlStateManager.func_179114_b((float)(t * 3.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
        this.drawVboOrList(starVBO, starGLCallList);
        GlStateManager.func_179121_F();
        GlStateManager.func_179094_E();
        GlStateManager.func_179114_b((float)t, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179131_c((float)0.5f, (float)1.0f, (float)1.0f, (float)alpha);
        this.drawVboOrList(starVBO, starGLCallList);
        GlStateManager.func_179121_F();
        GlStateManager.func_179094_E();
        GlStateManager.func_179114_b((float)(t * 2.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179131_c((float)1.0f, (float)0.75f, (float)0.75f, (float)alpha);
        this.drawVboOrList(starVBO, starGLCallList);
        GlStateManager.func_179121_F();
        GlStateManager.func_179094_E();
        GlStateManager.func_179114_b((float)(t * 3.0f), (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)(0.25f * alpha));
        this.drawVboOrList(starVBO, starGLCallList);
        GlStateManager.func_179121_F();
        GlStateManager.func_179094_E();
        GlStateManager.func_179114_b((float)t, (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.func_179131_c((float)0.5f, (float)1.0f, (float)1.0f, (float)(0.25f * alpha));
        this.drawVboOrList(starVBO, starGLCallList);
        GlStateManager.func_179121_F();
        GlStateManager.func_179094_E();
        GlStateManager.func_179114_b((float)(t * 2.0f), (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.func_179131_c((float)1.0f, (float)0.75f, (float)0.75f, (float)(0.25f * alpha));
        this.drawVboOrList(starVBO, starGLCallList);
        GlStateManager.func_179121_F();
        GlStateManager.func_179121_F();
    }

    private void drawVboOrList(VertexBuffer vbo, int displayList) {
        if (OpenGlHelper.func_176075_f()) {
            vbo.func_177359_a();
            GlStateManager.func_187410_q((int)32884);
            GlStateManager.func_187420_d((int)3, (int)5126, (int)12, (int)0);
            vbo.func_177358_a(7);
            vbo.func_177361_b();
            GlStateManager.func_187429_p((int)32884);
        } else {
            GlStateManager.func_179148_o((int)displayList);
        }
    }
}

