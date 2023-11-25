package com.divot.tetraenlarged;

import com.divot.tetraenlarged.items.modular.impl.ModularLargeBladedItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import se.mickelus.tetra.TetraMod;
import se.mickelus.tetra.TetraRegistries;


public class TetraEnlargedRegistries {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TetraMod.MOD_ID);

    public static void init(IEventBus bus) {
        bus.register(TetraRegistries.class);

        ITEMS.register(bus);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // ITEMS
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ITEMS.register(ModularLargeBladedItem.identifier, ModularLargeBladedItem::new);

    }

}
