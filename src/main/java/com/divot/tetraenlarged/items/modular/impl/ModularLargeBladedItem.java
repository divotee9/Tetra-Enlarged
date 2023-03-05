package com.divot.tetraenlarged.items.modular.impl;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ObjectHolder;
import se.mickelus.mutil.network.PacketHandler;
import se.mickelus.tetra.ConfigHandler;
import se.mickelus.tetra.TetraMod;
import se.mickelus.tetra.data.DataManager;
import se.mickelus.tetra.items.TetraItemGroup;
import se.mickelus.tetra.items.modular.IModularItem;
import se.mickelus.tetra.items.modular.ItemModularHandheld;
import se.mickelus.tetra.module.SchematicRegistry;
import se.mickelus.tetra.module.schematic.RemoveSchematic;
import se.mickelus.tetra.module.schematic.RepairSchematic;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ModularLargeBladedItem extends ItemModularHandheld {
    public final static String bladeKey = "greatsword/blade";
    public final static String hiltKey = "greatsword/hilt";

    public final static String guardKey = "greatsword/guard";
    public final static String pommelKey = "greatsword/pommel";
    public final static String fullerKey = "greatsword/fuller";

    public static final String identifier = "modular_greatsword";

    // @ObjectHolder(TetraMod.MOD_ID + ":" + identifier)
    @ObjectHolder(
            registryName = "item",
            value = "tetra:modular_greatsword"
    )
    public static ModularLargeBladedItem instance;

    public ModularLargeBladedItem() {
        super(new Item.Properties().stacksTo(1).tab(TetraItemGroup.instance).fireResistant());

        blockDestroyDamage = 2;

        majorModuleKeys = new String[]{bladeKey, hiltKey};
        minorModuleKeys = new String[]{fullerKey, guardKey, pommelKey};

        requiredModules = new String[]{bladeKey, hiltKey};

        updateConfig(ConfigHandler.honeSwordBase.get(), ConfigHandler.honeSwordIntegrityMultiplier.get());

        SchematicRegistry.instance.registerSchematic(new RepairSchematic(this, identifier));
        RemoveSchematic.registerRemoveSchematics(this, identifier);
    }

    @Override
    public void commonInit(PacketHandler packetHandler) {
        DataManager.instance.synergyData.onReload(() -> synergies = DataManager.instance.getSynergyData("greatsword/"));
    }

    public void updateConfig(int honeBase, int honeIntegrityMultiplier) {
        this.honeBase = honeBase;
        this.honeIntegrityMultiplier = honeIntegrityMultiplier;
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (allowedIn(group)) {
            items.add(setupGreatSwordStack("iron", "stick"));
        }
    }

    private ItemStack setupGreatSwordStack(String bladeMaterial, String hiltMaterial) {
        ItemStack itemStack = new ItemStack(this);

        IModularItem.putModuleInSlot(itemStack, bladeKey, "greatsword/heavy_blade", "greatsword/heavy_blade_material", "heavy_blade/" + bladeMaterial);
        IModularItem.putModuleInSlot(itemStack, hiltKey, "greatsword/basic_hilt", "greatsword/basic_hilt_material", "basic_hilt/" + hiltMaterial);
        IModularItem.putModuleInSlot(itemStack, guardKey, "greatsword/makeshift_guard", "greatsword/makeshift_guard_material", "makeshift_guard/" + bladeMaterial);
        IModularItem.putModuleInSlot(itemStack, pommelKey, "greatsword/decorative_pommel", "greatsword/decorative_pommel_material", "decorative_pommel/" + bladeMaterial);

        IModularItem.updateIdentifier(itemStack);

        return itemStack;
    }
    @Override
    public String getModelCacheKey(ItemStack itemStack, LivingEntity entity) {
        if (isThrowing(itemStack, entity)) {
            return super.getModelCacheKey(itemStack, entity) + ":throwing";
        }

        if (isBlocking(itemStack, entity)) {
            return super.getModelCacheKey(itemStack, entity) + ":blocking";
        }

        return super.getModelCacheKey(itemStack, entity);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public String getTransformVariant(ItemStack itemStack, @Nullable LivingEntity entity) {
        if (isThrowing(itemStack, entity)) {
            return "throwing";
        }
        if (isBlocking(itemStack, entity)) {
            return "blocking";
        }
        return null;
    }
}
