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
import net.omen.eclipsemod.item.ModItems;

public class ThrowingKnifeProjectileEntity extends ThrowableItemProjectile {

    public ThrowingKnifeProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ThrowingKnifeProjectileEntity(Level pLevel) {
        super(ModEntities.THROWING_KNIFE_PROJECTILE.get(), pLevel);
    }

    public ThrowingKnifeProjectileEntity(Level pLevel, LivingEntity livingEntity) {
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

        spawnAtLocation(getDefaultItem().asItem());

        this.discard();
    }
}
