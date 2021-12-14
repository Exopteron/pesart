package com.exomatic.pesart.entities;

import com.exomatic.pesart.Reference;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PesartEntitiesInitializer {
    public static final EntityType<CubeEntity> CUBE = Registry.register(Registry.ENTITY_TYPE, new Identifier(Reference.MODID, "cube_entity"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CubeEntity::new).dimensions(EntityDimensions.fixed(0.75F, 0.75F)).build());
    public static void setup() {
        FabricDefaultAttributeRegistry.register(CUBE, CubeEntity.createMobAttributes());
    }
}
