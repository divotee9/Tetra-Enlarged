package com.divot.tetraenlarged;

import com.divot.tetraenlarged.items.modular.impl.ModularLargeBladedItem;
import se.mickelus.mutil.gui.GuiAttachment;
import se.mickelus.mutil.gui.GuiElement;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloItemGui;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloItemsGui;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloMaterialsButtonGui;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
public class EnlargedHoloItemsGui extends GuiElement {

    public EnlargedHoloItemsGui (int x, int y, int width, int height, Consumer<IModularItem> onItemSelect, Consumer<String> onSlotSelect, Runnable onMaterialsClick) {
        super(x, y, width, height);

        addChild(new HoloItemGui(-119, 0, ModularLargeBladedItem.instance, 0, () -> onItemSelect.accept(ModularLargeBladedItem.instance), onSlotSelect).setAttachment(GuiAttachment.topCenter));

    }


}


