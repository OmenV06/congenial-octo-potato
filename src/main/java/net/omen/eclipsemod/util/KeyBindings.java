package net.omen.eclipsemod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {

    public static final String KEY_CATEGORY_ECLIPSE = "key.category.eclipsemod.eclipse";

    public static final String KEY_PLAYER_MENU = "key.eclipsemod.player_menu";

    public static final String KEY_TEMP_KEY = "key.eclipsemod.temp_key";



    public static final KeyMapping PLAYER_MENU = new KeyMapping(KEY_PLAYER_MENU, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_DOWN, KEY_CATEGORY_ECLIPSE);

    public static final KeyMapping TEMP_KEY = new KeyMapping(KEY_TEMP_KEY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Z, KEY_CATEGORY_ECLIPSE);

}
