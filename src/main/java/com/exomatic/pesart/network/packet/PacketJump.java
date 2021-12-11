package com.exomatic.pesart.network.packet;

import com.exopteron.network.ExoPacket;
import com.exopteron.network.Side;

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
