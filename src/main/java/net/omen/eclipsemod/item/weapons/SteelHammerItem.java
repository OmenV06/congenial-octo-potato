package net.omen.eclipsemod.item.weapons;

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
import net.omen.eclipsemod.init.ModCreativeModeTabs;
import net.omen.eclipsemod.init.ModItems;

import java.util.List;

public class SteelHammerItem extends SwordItem {

    public SteelHammerItem()
    {
        super(STEEL_HAMMER, 4, -2.8f, new Item.Properties().tab(ModCreativeModeTabs.ECLIPSE_TAB));
    }

    public static final Tier STEEL_HAMMER = TierSortingRegistry.registerTier(
            new ForgeTier(3, 600, 2.0f, 3f, 12,
                   Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(ModItems.STEEL_INGOT.get())),
            new ResourceLocation(EclipseMod.MOD_ID, "steel_hammer"), List.of(Tiers.IRON), List.of());

}

