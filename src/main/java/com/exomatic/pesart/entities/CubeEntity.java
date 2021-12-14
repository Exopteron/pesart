/* 
Copyright (c) 2021 Exopteron, PrismaticYT

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
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
            if (closestPlayer != null) {
                this.jumpControl.setActive();
                this.moveControl.moveTo(closestPlayer.getX(), closestPlayer.getY(), closestPlayer.getZ(), 1);
            }
        }
        super.tick();
    }
}
