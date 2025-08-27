/*
 * Converted to 1.7.10 compatibility
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
    public static MethodHandle glSkyList_getter;
    public static final String[] STAR_GL_CALL_LIST;
    public static final String[] GL_SKY_LIST;

    static {
        STAR_GL_CALL_LIST = new String[]{"starGLCallList", "field_72772_v", "p"};
        GL_SKY_LIST = new String[]{"glSkyList", "field_72771_w", "q"};
        try {
            Field f = ReflectionHelper.findField(RenderGlobal.class, (String[])STAR_GL_CALL_LIST);
            f.setAccessible(true);
            starGLCallList_getter = MethodHandles.publicLookup().unreflectGetter(f);
            f = ReflectionHelper.findField(RenderGlobal.class, (String[])GL_SKY_LIST);
            f.setAccessible(true);
            glSkyList_getter = MethodHandles.publicLookup().unreflectGetter(f);
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException("Failiure in getting class data for the Garden of Glass Skybox", e);
        }
    }
}

