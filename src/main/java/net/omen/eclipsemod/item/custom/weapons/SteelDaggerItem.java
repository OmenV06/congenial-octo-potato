package net.omen.eclipsemod.item.custom.weapons;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.TierSortingRegistry;
import net.omen.eclipsemod.EclipseMod;
import net.omen.eclipsemod.item.ModCreativeModeTabs;
import net.omen.eclipsemod.item.ModItems;

import java.util.List;

public class SteelDaggerItem extends SwordItem {

    public static final Tier STEEL_DAGGER = TierSortingRegistry.registerTier(
            new ForgeTier(3, 600, 2.0f, 3f, 12,
                   Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(ModItems.STEEL_INGOT.get())),
            new ResourceLocation(EclipseMod.MOD_ID, "steel_dagger"), List.of(Tiers.IRON), List.of());

    public SteelDaggerItem() {
        super(STEEL_DAGGER, 1, -1.6f, new Item.Properties().tab(ModCreativeModeTabs.ECLIPSE_TAB));
    }

}

