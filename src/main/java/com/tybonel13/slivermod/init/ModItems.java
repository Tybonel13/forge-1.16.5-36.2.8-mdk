package com.tybonel13.slivermod.init;

import com.tybonel13.slivermod.Slivers;
import com.tybonel13.slivermod.items.ModSpawnEggItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Slivers.MOD_ID);

    //Items
    public static final RegistryObject<ModSpawnEggItem> SLIVER_SPAWN_EGG = ITEMS.register("sliver_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.SLIVER, 0xB47B7A, 0x843E66, new Item.Properties().group(Slivers.TAB)));

    public static void register(IEventBus eventBus) {

        ITEMS.register(eventBus);
    }
}
