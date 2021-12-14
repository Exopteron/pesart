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

import com.exopteron.exoepiclib.network.ExoPacket;
import com.exopteron.exoepiclib.network.Side;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;

public class PacketJump extends ExoPacket {
    public PacketJump() {

    }
    @Override
    public void read(PacketByteBuf buf) {
        
    }

    @Override
    public void write(PacketByteBuf buf) {
 
    }

    @Override
    public void handle(PlayerEntity player, Side side) {
        if (side == Side.LOGICAL_CLIENT) {
            player.jump();
        }
    }
    
}
