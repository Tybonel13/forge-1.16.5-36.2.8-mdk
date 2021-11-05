package com.tybonel13.slivermod.init;

import com.tybonel13.slivermod.Slivers;
import com.tybonel13.slivermod.entities.SliverEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Slivers.MOD_ID);

    public static final RegistryObject<EntityType<SliverEntity>> SLIVER = ENTITY_TYPES.register("sliver",
            () -> EntityType.Builder.create(SliverEntity::new, EntityClassification.MONSTER)
                    .size(0.5625f, 1.5f)
                    .build(new ResourceLocation(Slivers.MOD_ID, "sliver").toString()));
}
