package com.tybonel13.slivermod.world.gen;

import com.tybonel13.slivermod.Slivers;
import com.tybonel13.slivermod.entities.SliverEntity;
import com.tybonel13.slivermod.init.ModEntityTypes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = Slivers.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntitySpawns {

    public static void onEntitySpawn(final BiomeLoadingEvent event) {
        if(event.getCategory() != Biome.Category.NETHER || event.getCategory() != Biome.Category.THEEND || event.getCategory() != Biome.Category.OCEAN){
            List<MobSpawnInfo.Spawners> base = event.getSpawns().getSpawner(ModEntityTypes.SLIVER.get().getClassification());
            base.add(new MobSpawnInfo.Spawners(ModEntityTypes.SLIVER.get(), 50, 5, 8));
        }
    }
}
