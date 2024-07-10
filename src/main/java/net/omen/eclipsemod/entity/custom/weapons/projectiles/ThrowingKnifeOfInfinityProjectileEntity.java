package net.omen.eclipsemod.entity.custom.weapons.projectiles;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.omen.eclipsemod.entity.ModEntities;
import net.omen.eclipsemod.init.ModItems;

public class ThrowingKnifeOfInfinityProjectileEntity extends ThrowableItemProjectile {

    public ThrowingKnifeOfInfinityProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ThrowingKnifeOfInfinityProjectileEntity(Level pLevel) {
        super(ModEntities.THROWING_KNIFE_PROJECTILE.get(), pLevel);
    }

    public ThrowingKnifeOfInfinityProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.THROWING_KNIFE_PROJECTILE.get(), livingEntity, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.THROWING_KNIFE.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);

        pResult.getEntity().hurt(DamageSource.GENERIC, 2.0F);

        this.discard();

    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);

        this.discard();
    }
}
