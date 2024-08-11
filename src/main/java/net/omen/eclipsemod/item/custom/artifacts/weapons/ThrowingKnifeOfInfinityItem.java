package net.omen.eclipsemod.item.custom.artifacts.weapons;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.omen.eclipsemod.entity.custom.weapons.projectiles.ThrowingKnifeOfInfinityProjectileEntity;
import net.omen.eclipsemod.entity.custom.weapons.projectiles.ThrowingKnifeProjectileEntity;
import net.omen.eclipsemod.item.ModCreativeModeTabs;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ThrowingKnifeOfInfinityItem extends Item {

    public ThrowingKnifeOfInfinityItem() {
        super (new Item.Properties().stacksTo(1).tab(ModCreativeModeTabs.ECLIPSE_TAB));
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
            ThrowingKnifeOfInfinityProjectileEntity knife = new ThrowingKnifeOfInfinityProjectileEntity(pLevel, pPlayer);
            knife.setItem(itemStack);
            knife.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 3.0F, 1.0F);
            pLevel.addFreshEntity(knife);
        }

        pPlayer.getCooldowns().addCooldown(this, 10);

        return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        pTooltipComponents.add(Component.translatable("tooltip.eclipsemod.common.tooltip").withStyle(ChatFormatting.GRAY));
        pTooltipComponents.add(Component.translatable("tooltip.eclipsemod.throwing_knife.tooltip"));
        pTooltipComponents.add(Component.translatable("tooltip.eclipsemod.throwing_knife_of_infinity.tooltip"));
    }
}
