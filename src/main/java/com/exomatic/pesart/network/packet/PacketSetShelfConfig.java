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
package com.exomatic.pesart.network.packet;

import com.exomatic.pesart.blocks.entities.ShelfBlockEntity;
import com.exopteron.network.ExoPacket;
import com.exopteron.network.Side;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class PacketSetShelfConfig extends ExoPacket {
    public PacketSetShelfConfig() {
        
    }
    public BlockPos pos;
    public boolean showTag;
    public PacketSetShelfConfig(BlockPos pos, boolean showTag) {
        this.pos = pos;
        this.showTag = showTag;
    }
    @Override
    public void read(PacketByteBuf buf) {
        this.pos = buf.readBlockPos();
        this.showTag = buf.readBoolean();
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
        buf.writeBoolean(this.showTag);
    }

    @Override
    public void handle(PlayerEntity player, Side side) {
        if (side == Side.LOGICAL_SERVER) {
            if (player.getPos().distanceTo(new Vec3d(this.pos.getX(), this.pos.getY(), this.pos.getZ())) < 10.0) {
                if (player instanceof ServerPlayerEntity serverPlayer) {
                    BlockEntity blockEntity = serverPlayer.world.getBlockEntity(this.pos);
                    if (blockEntity != null && blockEntity instanceof ShelfBlockEntity shelfBlockEntity) {
                        shelfBlockEntity.showAlways = this.showTag;
                    }
                }
            }
        }
    }
}
