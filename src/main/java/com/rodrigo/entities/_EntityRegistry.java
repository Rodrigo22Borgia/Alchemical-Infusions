package com.rodrigo.entities;

import com.rodrigo.blocks._BlockRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import static com.rodrigo.AlchemicalInfusions.modid;

public class _EntityRegistry {
    public static final BlockEntityType<AlchemyEntity> alchemyEntity = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(modid, "alchemy_entity"),
            FabricBlockEntityTypeBuilder.create(AlchemyEntity::new, _BlockRegistry.alchemy_set).build()//.build(Util.getChoiceType(TypeReferences.BLOCK_ENTITY, "alchemy_entity"))
    );
    public static final BlockEntityType<InfusionEntity> infusionEntity = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(modid, "infusion_entity"),
            FabricBlockEntityTypeBuilder.create(InfusionEntity::new, _BlockRegistry.infusion_anvil).build()//.build(Util.getChoiceType(TypeReferences.BLOCK_ENTITY, "infusion_entity"))
    );

    public static void init() {}
}
