package net.omen.eclipsemod.item.artifacts.weapons;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.TierSortingRegistry;
import net.omen.eclipsemod.EclipseMod;
import net.omen.eclipsemod.entity.custom.weapons.projectiles.KatanaOfWindProjectileEntity;
import net.omen.eclipsemod.init.ModCreativeModeTabs;
import net.omen.eclipsemod.init.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KatanaOfWindItem extends SwordItem {

    public static final Tier KATANA_OF_WIND = TierSortingRegistry.registerTier(
            new ForgeTier(3, 0, 2.0f, 3f, 20,
                    Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(ModItems.ILLUMINITE_INGOT.get())),
            new ResourceLocation(EclipseMod.MOD_ID, "katana_of_wind"), List.of(Tiers.DIAMOND), List.of());



    public KatanaOfWindItem() {
        super(KATANA_OF_WIND, 2, -1.8f, new Item.Properties().tab(ModCreativeModeTabs.ECLIPSE_TAB));
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.NEUTRAL, 0.3F, 1.2F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));




        if (pPlayer.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.fail(itemStack);
        }



        if (!pLevel.isClientSide) {
            KatanaOfWindProjectileEntity blade = new KatanaOfWindProjectileEntity(pLevel, pPlayer);
            blade.setItem(itemStack);
            blade.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 2.0F, 1.0F);
            pLevel.addFreshEntity(blade);
        }

        pPlayer.getCooldowns().addCooldown(this, 60);

        return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        pTooltipComponents.add(Component.translatable("tooltip.eclipsemod.rare.tooltip").withStyle(ChatFormatting.BLUE));
        pTooltipComponents.add(Component.translatable("tooltip.eclipsemod.katana_of_wind.tooltip"));
    }


}

