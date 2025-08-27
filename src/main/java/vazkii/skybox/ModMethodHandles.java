/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 */
package vazkii.skybox;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public final class ModMethodHandles {
    public static MethodHandle starGLCallList_getter;
    public static MethodHandle starVBO_getter;
    public static MethodHandle glSkyList_getter;
    public static MethodHandle skyVBO_getter;
    public static final String[] STAR_GL_CALL_LIST;
    public static final String[] STAR_VBO;
    public static final String[] GL_SKY_LIST;
    public static final String[] SKY_VBO;

    static {
        STAR_GL_CALL_LIST = new String[]{"starGLCallList", "field_72772_v", "p"};
        STAR_VBO = new String[]{"starVBO", "field_175013_s", "t"};
        GL_SKY_LIST = new String[]{"glSkyList", "field_72771_w", "q"};
        SKY_VBO = new String[]{"skyVBO", "field_175012_t", "u"};
        try {
            Field f = ReflectionHelper.findField(RenderGlobal.class, (String[])STAR_GL_CALL_LIST);
            f.setAccessible(true);
            starGLCallList_getter = MethodHandles.publicLookup().unreflectGetter(f);
            f = ReflectionHelper.findField(RenderGlobal.class, (String[])STAR_VBO);
            f.setAccessible(true);
            starVBO_getter = MethodHandles.publicLookup().unreflectGetter(f);
            f = ReflectionHelper.findField(RenderGlobal.class, (String[])GL_SKY_LIST);
            f.setAccessible(true);
            glSkyList_getter = MethodHandles.publicLookup().unreflectGetter(f);
            f = ReflectionHelper.findField(RenderGlobal.class, (String[])SKY_VBO);
            f.setAccessible(true);
            skyVBO_getter = MethodHandles.publicLookup().unreflectGetter(f);
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException("Failiure in getting class data for the Garden of Glass Skybox", e);
        }
    }
}

