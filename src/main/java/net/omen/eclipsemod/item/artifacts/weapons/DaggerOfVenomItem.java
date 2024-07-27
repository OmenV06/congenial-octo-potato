package net.omen.eclipsemod.item.artifacts.weapons;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.TierSortingRegistry;
import net.omen.eclipsemod.EclipseMod;
import net.omen.eclipsemod.init.ModCreativeModeTabs;
import net.omen.eclipsemod.init.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DaggerOfVenomItem extends SwordItem {

    public static final Tier DAGGER_OF_VENOM = TierSortingRegistry.registerTier(
            new ForgeTier(3, 800, 2.0f, 3f, 20,
                    Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(ModItems.ILLUMINITE_INGOT.get())),
            new ResourceLocation(EclipseMod.MOD_ID, "dagger_of_venom"), List.of(Tiers.DIAMOND), List.of());



    public DaggerOfVenomItem() {
        super(DAGGER_OF_VENOM, 1, -1.6f, new Item.Properties().tab(ModCreativeModeTabs.ECLIPSE_TAB));

    }



    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
         {
            target.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 1));
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        pTooltipComponents.add(Component.translatable("tooltip.eclipsemod.uncommon.tooltip").withStyle(ChatFormatting.GREEN));
        pTooltipComponents.add(Component.translatable("tooltip.eclipsemod.dagger_of_venom.tooltip"));
    }


}

