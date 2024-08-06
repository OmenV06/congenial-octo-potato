package net.omen.eclipsemod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.omen.eclipsemod.EclipseMod;
import net.omen.eclipsemod.entity.custom.weapons.projectiles.KatanaOfWindProjectileEntity;
import net.omen.eclipsemod.entity.custom.weapons.projectiles.ThrowingKnifeProjectileEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EclipseMod.MOD_ID);

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    //Add entities here
    public static final RegistryObject<EntityType<ThrowingKnifeProjectileEntity>> THROWING_KNIFE_PROJECTILE =
            ENTITY_TYPES.register("throwing_knife_projectile", () -> EntityType.Builder.<ThrowingKnifeProjectileEntity>of(ThrowingKnifeProjectileEntity::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f).build("throwing_knife_projectile"));
    public static final RegistryObject<EntityType<KatanaOfWindProjectileEntity>> KATANA_OF_WIND_PROJECTILE =
            ENTITY_TYPES.register("katana_of_wind_projectile", () -> EntityType.Builder.<KatanaOfWindProjectileEntity>of(KatanaOfWindProjectileEntity::new, MobCategory.MISC)
                    .sized(1.25f, 0.25f).build("katana_of_wind_projectile"));

}
