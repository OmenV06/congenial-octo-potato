package net.omen.eclipsemod.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.omen.eclipsemod.EclipseMod;
import net.omen.eclipsemod.item.artifacts.weapons.DaggerOfVenomItem;
import net.omen.eclipsemod.item.artifacts.weapons.KatanaOfWindItem;
import net.omen.eclipsemod.item.artifacts.weapons.MaceOfDensityItem;
import net.omen.eclipsemod.item.artifacts.weapons.ThrowingKnifeOfInfinityItem;
import net.omen.eclipsemod.item.misc.EboniteIngotItem;
import net.omen.eclipsemod.item.misc.IlluminiteIngotItem;
import net.omen.eclipsemod.item.misc.SteelIngotItem;
import net.omen.eclipsemod.item.misc.SteelNuggetItem;
import net.omen.eclipsemod.item.weapons.SteelDaggerItem;
import net.omen.eclipsemod.item.weapons.SteelHammerItem;
import net.omen.eclipsemod.item.weapons.ThrowingKnifeItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EclipseMod.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    //Items added here
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", SteelIngotItem::new);
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget", SteelNuggetItem::new);
    public static final RegistryObject<Item> ILLUMINITE_INGOT = ITEMS.register("illuminite_ingot", IlluminiteIngotItem::new);
    public static final RegistryObject<Item> EBONITE_INGOT = ITEMS.register("ebonite_ingot", EboniteIngotItem::new);

    public static final RegistryObject<Item> THROWING_KNIFE = ITEMS.register("throwing_knife", ThrowingKnifeItem::new);
    public static final RegistryObject<Item> STEEL_DAGGER = ITEMS.register("steel_dagger", SteelDaggerItem::new);
    public static final RegistryObject<Item> STEEL_HAMMER = ITEMS.register("steel_hammer", SteelHammerItem::new);

    public static final RegistryObject<Item> THROWING_KNIFE_OF_INFINITY = ITEMS.register("throwing_knife_of_infinity", ThrowingKnifeOfInfinityItem::new);
    public static final RegistryObject<Item> DAGGER_OF_VENOM = ITEMS.register("dagger_of_venom", DaggerOfVenomItem::new);
    public static final RegistryObject<Item> MACE_OF_DENSITY = ITEMS.register("mace_of_density", MaceOfDensityItem::new);
    public static final RegistryObject<Item> KATANA_OF_WIND = ITEMS.register("katana_of_wind", KatanaOfWindItem::new);
    //Make gui icon change from handle to handle and blade, and finish hitblock method

    public static final RegistryObject<Item> ECLIPSE_ICON = ITEMS.register("eclipse_icon",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> KATANA_OF_WIND_PROJECTILE_ITEM = ITEMS.register("katana_of_wind_projectile_item",
            () -> new Item(new Item.Properties()));


}
