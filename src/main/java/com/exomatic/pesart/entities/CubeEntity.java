package com.exomatic.pesart.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class CubeEntity extends PathAwareEntity {

    protected CubeEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }
    @Override
    public void tick() {
        if (!this.world.isClient) {
            ServerPlayerEntity closestPlayer = null;
            for (ServerPlayerEntity p : ((ServerWorld) this.world).getPlayers()) {
                if (closestPlayer == null) {
                    closestPlayer = p;
                    continue;
                }
                if (closestPlayer.distanceTo(this) > p.distanceTo(this)) {
                    closestPlayer = p;
                }
            }
            this.jumpControl.setActive();
            this.moveControl.moveTo(closestPlayer.getX(), closestPlayer.getY(), closestPlayer.getZ(), 1);
        }
        super.tick();
    }
}
