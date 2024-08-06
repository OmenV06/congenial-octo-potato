package net.omen.eclipsemod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.omen.eclipsemod.block.ModBlocks;
import net.omen.eclipsemod.entity.ModEntities;
import net.omen.eclipsemod.entity.client.renderers.weapons.projectiles.KatanaOfWindProjectileRenderer;
import net.omen.eclipsemod.item.ModItems;
import net.omen.eclipsemod.networking.ModPackets;
import net.omen.eclipsemod.power.PowerHandler;
import net.omen.eclipsemod.util.commands.CheckPowerCommand;
import net.omen.eclipsemod.util.commands.SetPowerCommand;
import org.slf4j.Logger;



@Mod(EclipseMod.MOD_ID)
public class EclipseMod {
    public static final String MOD_ID = "eclipsemod";

    private static final Logger LOGGER = LogUtils.getLogger();



    public EclipseMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(PowerHandler.class);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }



    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {



        });

        ModPackets.register();

    }



    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }



    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            EntityRenderers.register(ModEntities.THROWING_KNIFE_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.KATANA_OF_WIND_PROJECTILE.get(), KatanaOfWindProjectileRenderer::new);

        }
    }

    @Mod.EventBusSubscriber
    public class CommandRegister {

        @SubscribeEvent
        public static void onRegisterCommandsEvent(RegisterCommandsEvent event) {
            // Register commands here
            SetPowerCommand.register(event.getDispatcher());
            CheckPowerCommand.register(event.getDispatcher());

        }
    }

}