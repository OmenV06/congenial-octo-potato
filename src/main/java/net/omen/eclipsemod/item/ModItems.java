package net.omen.eclipsemod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.omen.eclipsemod.EclipseMod;
import net.omen.eclipsemod.item.custom.artifacts.weapons.DaggerOfVenomItem;
import net.omen.eclipsemod.item.custom.artifacts.weapons.KatanaOfWindItem;
import net.omen.eclipsemod.item.custom.artifacts.weapons.MaceOfDensityItem;
import net.omen.eclipsemod.item.custom.artifacts.weapons.ThrowingKnifeOfInfinityItem;
import net.omen.eclipsemod.item.custom.weapons.SteelDaggerItem;
import net.omen.eclipsemod.item.custom.weapons.SteelHammerItem;
import net.omen.eclipsemod.item.custom.weapons.ThrowingKnifeItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EclipseMod.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    //Items added here

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ECLIPSE_TAB)));
    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ECLIPSE_TAB)));
    public static final RegistryObject<Item> ILLUMINITE_INGOT = ITEMS.register("illuminite_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ECLIPSE_TAB)));
    public static final RegistryObject<Item> EBONITE_INGOT = ITEMS.register("ebonite_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.ECLIPSE_TAB)));

    public static final RegistryObject<Item> THROWING_KNIFE = ITEMS.register("throwing_knife",
            () -> new ThrowingKnifeItem(new Item.Properties().stacksTo(16)
                    .tab(ModCreativeModeTabs.ECLIPSE_TAB)));
    public static final RegistryObject<Item> STEEL_DAGGER = ITEMS.register("steel_dagger",
            () -> new SwordItem(SteelDaggerItem.STEEL_DAGGER, 1, -1.6f, new Item.Properties()
                    .tab(ModCreativeModeTabs.ECLIPSE_TAB)));
    public static final RegistryObject<Item> STEEL_HAMMER = ITEMS.register("steel_hammer",
            () -> new SwordItem(SteelHammerItem.STEEL_HAMMER, 4, -2.8f, new Item.Properties()
                    .tab(ModCreativeModeTabs.ECLIPSE_TAB)));

    public static final RegistryObject<Item> THROWING_KNIFE_OF_INFINITY = ITEMS.register("throwing_knife_of_infinity",
            () -> new ThrowingKnifeOfInfinityItem(new Item.Properties().stacksTo(1)
                    .tab(ModCreativeModeTabs.ECLIPSE_TAB)));
    public static final RegistryObject<Item> DAGGER_OF_VENOM = ITEMS.register("dagger_of_venom",
            () -> new DaggerOfVenomItem(DaggerOfVenomItem.DAGGER_OF_VENOM, 1, -1.6f, new Item.Properties()
                    .tab(ModCreativeModeTabs.ECLIPSE_TAB)));
    public static final RegistryObject<Item> MACE_OF_DENSITY = ITEMS.register("mace_of_density",
            () -> new MaceOfDensityItem(MaceOfDensityItem.MACE_OF_DENSITY, 6, -2.8f, new Item.Properties()
                    .tab(ModCreativeModeTabs.ECLIPSE_TAB)));
    public static final RegistryObject<Item> KATANA_OF_WIND = ITEMS.register("katana_of_wind",
            () -> new KatanaOfWindItem(KatanaOfWindItem.KATANA_OF_WIND, 2, -1.8f, new Item.Properties()
                    .tab(ModCreativeModeTabs.ECLIPSE_TAB))); //Make gui icon change from handle to handle and blade, and finish hitblock method

    public static final RegistryObject<Item> ECLIPSE_ICON = ITEMS.register("eclipse_icon",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> KATANA_OF_WIND_PROJECTILE_ITEM = ITEMS.register("katana_of_wind_projectile_item",
            () -> new Item(new Item.Properties()));


}
