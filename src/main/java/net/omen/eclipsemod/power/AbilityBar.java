package net.omen.eclipsemod.power;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.omen.eclipsemod.EclipseMod;
import net.omen.eclipsemod.util.KeyBindings;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class AbilityBar {
    private static boolean isActive = false;
    private static final Minecraft mc = Minecraft.getInstance();
    private static final ResourceLocation ABILITY_BAR_ICON = new ResourceLocation(EclipseMod.MOD_ID, "textures/gui/ability_bar_texture.png");

    private static final KeyMapping[] HOTBAR_KEYS = new KeyMapping[]{
            mc.options.keyHotbarSlots[0],
            mc.options.keyHotbarSlots[1],
            mc.options.keyHotbarSlots[2],
            mc.options.keyHotbarSlots[3],
            mc.options.keyHotbarSlots[4],
            mc.options.keyHotbarSlots[5],
            mc.options.keyHotbarSlots[6],
            mc.options.keyHotbarSlots[7],
            mc.options.keyHotbarSlots[8],
    };

    private static final KeyMapping[] ABILITY_KEYS = new KeyMapping[]{
            KeyBindings.ABILITY_ONE,
            KeyBindings.ABILITY_TWO,
            KeyBindings.ABILITY_THREE,
            KeyBindings.ABILITY_FOUR,
            KeyBindings.ABILITY_FIVE,
            KeyBindings.ABILITY_SIX,
            KeyBindings.ABILITY_SEVEN,
            KeyBindings.ABILITY_EIGHT,
            KeyBindings.ABILITY_NINE,
    };

    private static final Map<KeyMapping, InputConstants.Key> originalAbilityKeyMappings = new HashMap<>();

    public static void toggle() {
        isActive = !isActive;
        if (isActive) {
            unbindHotbarKeys();
            rebindAbilityKeys();
        } else {
            rebindHotbarKeys();
            unbindAbilityKeys();
        }



    }

    public static boolean isActive() {
        return isActive;
    }

    private static void unbindHotbarKeys() {
        for (KeyMapping key : HOTBAR_KEYS) {
            key.setKeyModifierAndCode(KeyModifier.NONE, InputConstants.UNKNOWN);
        }
    }

    private static void rebindHotbarKeys() {
        HOTBAR_KEYS[0].setKeyModifierAndCode(KeyModifier.NONE, InputConstants.getKey("key.keyboard.1"));
        HOTBAR_KEYS[1].setKeyModifierAndCode(KeyModifier.NONE, InputConstants.getKey("key.keyboard.2"));
        HOTBAR_KEYS[2].setKeyModifierAndCode(KeyModifier.NONE, InputConstants.getKey("key.keyboard.3"));
        HOTBAR_KEYS[3].setKeyModifierAndCode(KeyModifier.NONE, InputConstants.getKey("key.keyboard.4"));
        HOTBAR_KEYS[4].setKeyModifierAndCode(KeyModifier.NONE, InputConstants.getKey("key.keyboard.5"));
        HOTBAR_KEYS[5].setKeyModifierAndCode(KeyModifier.NONE, InputConstants.getKey("key.keyboard.6"));
        HOTBAR_KEYS[6].setKeyModifierAndCode(KeyModifier.NONE, InputConstants.getKey("key.keyboard.7"));
        HOTBAR_KEYS[7].setKeyModifierAndCode(KeyModifier.NONE, InputConstants.getKey("key.keyboard.8"));
        HOTBAR_KEYS[8].setKeyModifierAndCode(KeyModifier.NONE, InputConstants.getKey("key.keyboard.9"));
    }

    private static void unbindAbilityKeys() {
        for (KeyMapping key : ABILITY_KEYS) {
            originalAbilityKeyMappings.put(key, key.getKey());
            key.setKeyModifierAndCode(KeyModifier.NONE, InputConstants.UNKNOWN);
        }
    }

    private static void rebindAbilityKeys() {
        InputConstants.Key[] defaultKeys = new InputConstants.Key[]{
                InputConstants.getKey("key.keyboard.1"),
                InputConstants.getKey("key.keyboard.2"),
                InputConstants.getKey("key.keyboard.3"),
                InputConstants.getKey("key.keyboard.4"),
                InputConstants.getKey("key.keyboard.5"),
                InputConstants.getKey("key.keyboard.6"),
                InputConstants.getKey("key.keyboard.7"),
                InputConstants.getKey("key.keyboard.8"),
                InputConstants.getKey("key.keyboard.9"),
        };

        for (int i = 0; i < ABILITY_KEYS.length; i++) {
            KeyMapping key = ABILITY_KEYS[i];
            InputConstants.Key originalKey = originalAbilityKeyMappings.getOrDefault(key, defaultKeys[i]);

            // If the original key was unbound, rebind to the default key (1-9)
            if (originalKey == InputConstants.UNKNOWN) {
                originalKey = defaultKeys[i];
            }

            key.setKeyModifierAndCode(KeyModifier.NONE, originalKey);
        }
    }

    @SubscribeEvent
    public static void onRenderGuiOverlay(RenderGuiOverlayEvent.Pre event) {
        if (isActive && event.getOverlay().id().equals(VanillaGuiOverlay.HOTBAR.id())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onRenderAbilityBar(RenderGuiOverlayEvent.Post event) {
        if (isActive) {
            renderAbilityBarIcons(event.getPoseStack());
        }
    }

    private static void renderAbilityBarIcons(PoseStack poseStack) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, ABILITY_BAR_ICON);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        int iconSize = 20; // Size of each icon
        int padding = 2;   // Padding between icons
        int totalWidth = (iconSize * 9) + (padding * 8); // Total width of the ability bar
        int xStart = (mc.getWindow().getGuiScaledWidth() - totalWidth) / 2; // Center the ability bar
        int y = mc.getWindow().getGuiScaledHeight() - 22; // Y position of the icons

        for (int i = 0; i < 9; i++) {
            int x = xStart + (i * (iconSize + padding));
            Gui.blit(poseStack, x, y, 0, 0, iconSize, iconSize, iconSize, iconSize);
        }

        RenderSystem.disableBlend();
    }

}