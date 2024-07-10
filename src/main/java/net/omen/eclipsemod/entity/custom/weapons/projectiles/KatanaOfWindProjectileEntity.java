package net.omen.eclipsemod.entity.custom.weapons.projectiles;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
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

public class KatanaOfWindProjectileEntity extends ThrowableItemProjectile {

    public KatanaOfWindProjectileEntity(EntityType<? extends KatanaOfWindProjectileEntity> entityType, Level world) {
        super(entityType, world);
    }

    public KatanaOfWindProjectileEntity(Level world, LivingEntity owner) {
        super(ModEntities.KATANA_OF_WIND_PROJECTILE.get(), owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.KATANA_OF_WIND.get();
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.tickCount > 40) {

            despawnBlade(this);

        }
    }

    private void despawnBlade(KatanaOfWindProjectileEntity entity) {

        spawnParticles(entity);
        this.discard();

    }

    private static void spawnParticles(KatanaOfWindProjectileEntity entity) {

        if (entity.getLevel() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.POOF, entity.getX(), entity.getY(), entity.getZ(),
                    20, 0.75D, 0.25D, 0.25D, 0.0D);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        if (!this.level.isClientSide) {
            result.getEntity().hurt(DamageSource.GENERIC, 12.0F);
        }
        despawnBlade(this);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

            despawnBlade(this);

    }
}
