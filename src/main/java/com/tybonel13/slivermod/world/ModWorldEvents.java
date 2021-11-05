package com.tybonel13.slivermod.world;

import com.tybonel13.slivermod.Slivers;
import com.tybonel13.slivermod.world.gen.ModEntitySpawns;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = Slivers.MOD_ID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModEntitySpawns.onEntitySpawn(event);
    }
}
