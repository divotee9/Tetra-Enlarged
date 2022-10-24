package com.divot.tetraenlarged;

import com.divot.tetraenlarged.items.modular.impl.ModularLargeBladedItem;
import net.minecraftforge.eventbus.api.IEventBus;
import se.mickelus.tetra.TetraRegistries;


public class TetraEnlargedRegistries extends TetraRegistries {

    public static void init(IEventBus bus) {
        bus.register(TetraRegistries.class);

        items.register(bus);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // ITEMS
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        items.register(ModularLargeBladedItem.identifier, ModularLargeBladedItem::new);

    }

}
