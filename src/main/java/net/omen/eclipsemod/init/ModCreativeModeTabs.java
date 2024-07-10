package net.omen.eclipsemod.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {

    public static final CreativeModeTab ECLIPSE_TAB = new CreativeModeTab("eclipse_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ECLIPSE_ICON.get());
        }
    };
}