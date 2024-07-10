package net.omen.eclipsemod.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.omen.eclipsemod.EclipseMod;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, EclipseMod.MOD_ID);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    //Add blocks here
    public static final RegistryObject<Block> BLOCK_OF_STEEL = registerBlock("block_of_steel",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(7.0F, 8.0F)), ModCreativeModeTabs.ECLIPSE_TAB);
    public static final RegistryObject<Block> BLOCK_OF_ILLUMINITE = registerBlock("block_of_illuminite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(25.0F, 600.0F).sound(SoundType.COPPER)
                    .lightLevel(blockState -> 10)), ModCreativeModeTabs.ECLIPSE_TAB);
    public static final RegistryObject<Block> BLOCK_OF_EBONITE = registerBlock("block_of_ebonite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(25.0F, 600.0F).sound(SoundType.LODESTONE)
                    .lightLevel(blockState -> 1)), ModCreativeModeTabs.ECLIPSE_TAB); //May update sound
}
